/*------------------------------------------------------------------------------
 * NAME : MenuServiceImpl.java
 * DESC : 시스템 메뉴관련 요청에 대한 서비스 구현체
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/28  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.withtec.pfsms.common.Constants;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetDetailMenuResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetListAvailableMenuResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetMenuTreeListResponseDto;
import com.withtec.pfsms.entity.MenuByRoleEntity;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.entity.NameByLanguageEntity;
import com.withtec.pfsms.entity.RoleByUserEntity;
import com.withtec.pfsms.entity.UserEntity;
import com.withtec.pfsms.repository.MenuByRoleRepository;
import com.withtec.pfsms.repository.MenuRepository;
import com.withtec.pfsms.repository.NameByLanguageRepository;
import com.withtec.pfsms.repository.RoleByUserRepository;
import com.withtec.pfsms.repository.UserRepository;
import com.withtec.pfsms.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

  private final MenuRepository menuRepository;
  private final MenuByRoleRepository menuByRoleRepository;
  private final RoleByUserRepository roleByUserRepository;
  private final UserRepository userRepository;
  private final NameByLanguageRepository nameByLanguageRepository;

 	/*
   * 메뉴 트리 리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetMenuAllListResponseDto>
	*/
  @Override
  public ResponseEntity<? super GetMenuTreeListResponseDto> getMenuTreeList(UserDetails rsUserDetails) {

    String nationCd;
    List<MenuEntity> rsTreeMenuList = new ArrayList<>();

    try {
      
      if(rsUserDetails == null) return ResponseDto.noAuthization();

      UserEntity rsUser = userRepository.findByLoginId(rsUserDetails.getUsername());
      if(rsUser == null) return ResponseDto.notExistLoginId();

      nationCd = (rsUser.getNationCd() == null || rsUser.getNationCd().trim().isEmpty()) ? "ko" : rsUser.getNationCd();

      MenuEntity rsUpperMenu = menuRepository.findByMenuNo("MNU000");
      rsTreeMenuList = menuRepository.findByUpperMenu(rsUpperMenu);
      if(rsTreeMenuList == null) return GetMenuTreeListResponseDto.notExistedMenu();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return GetMenuTreeListResponseDto.success(nameByLanguageRepository, nationCd, rsTreeMenuList);
  }
  
 	/*
   * 선택 메뉴 상세내역
	 * 
	 * @param String menuNo
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetDetailMenuResponseDto>
	*/
  @Override
  public ResponseEntity<? super GetDetailMenuResponseDto> getDetailOfMenu(
    String menuNo,
    UserDetails rsUserDetails
  ) {

    MenuEntity rsMenu;
    List<NameByLanguageEntity> rsNameByLangList;

    try {
      
      if(rsUserDetails == null) return ResponseDto.noAuthization();

      UserEntity rsUser = userRepository.findByLoginId(rsUserDetails.getUsername());
      if(rsUser == null) return ResponseDto.notExistLoginId();

      rsMenu = menuRepository.findByMenuNo(menuNo);

      rsNameByLangList = nameByLanguageRepository.findByNmClCdAndNmCd(Constants.MENU_NAME, menuNo);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return GetDetailMenuResponseDto.success(rsMenu, rsNameByLangList);
  }
  
 	/*
   * 역활 별 사용가능 메뉴리스트
	 * 
	 * @param String upperMenuNo
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetListAvailableMenuResponseDto>
	*/
  @Override
  public ResponseEntity<? super GetListAvailableMenuResponseDto> getListOfAvailableMenusByRole(
    String upperMenuNo,
    UserDetails rsUserDetails
  ) {

    List<MenuEntity> rsListTopMenu = new ArrayList<>();
    List<MenuByRoleEntity> rsListAvailableMenu = new ArrayList<>();
    String nationCd;

    try {
      
      if(rsUserDetails == null) return ResponseDto.noAuthization();

      UserEntity rsUser = userRepository.findByLoginId(rsUserDetails.getUsername());
      if(rsUser == null) return ResponseDto.notExistLoginId();

      nationCd = (rsUser.getNationCd() == null || rsUser.getNationCd().trim().isEmpty()) ? "ko" : rsUser.getNationCd();

      MenuEntity rsUpperMenu = menuRepository.findByMenuNoAndIndictYn(upperMenuNo, "Y");
      rsListTopMenu = menuRepository.findByUpperMenuAndIndictYn(rsUpperMenu, "Y");
      if(rsListTopMenu == null) return GetListAvailableMenuResponseDto.availableTopMenu();

      // 사용자에게 할당된 역할
      List<RoleByUserEntity> rsListRoleByUser = roleByUserRepository.findByUserAndUseYn(rsUser, "Y");
      if(rsListRoleByUser == null) return ResponseDto.notExistRole();

      for(RoleByUserEntity rsRoleByUser : rsListRoleByUser) {
        List<MenuByRoleEntity> rsListMenuByRole = menuByRoleRepository.findByRoleAndUseYn(rsRoleByUser.getRole(), "Y");

        // rsListAvailableMenu에서 rsListMenuByRole 내역을 제거합니다.
        rsListAvailableMenu.removeAll(rsListMenuByRole);

        // rsListAvailableMenu에서 rsListMenuByRole 내역을 추가합니다.
        rsListAvailableMenu.addAll(rsListMenuByRole);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return GetListAvailableMenuResponseDto.success(nameByLanguageRepository, nationCd, rsListTopMenu, rsListAvailableMenu);
  }
}
