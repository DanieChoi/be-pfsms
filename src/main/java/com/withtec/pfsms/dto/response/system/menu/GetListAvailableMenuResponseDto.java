/*------------------------------------------------------------------------------
 * NAME : GetListAvailableMenuResponseDto.java
 * DESC : 로그인 사용자에 대한 사용가능한 메뉴리스트 요청에 대한 API 호출 시 반환하는 DTO
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
package com.withtec.pfsms.dto.response.system.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.object.AvailableMenuListItem;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.MenuByRoleEntity;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GetListAvailableMenuResponseDto extends ResponseDto {

  private List<AvailableMenuListItem> listAvailableMenus;
  
	/*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param String nationCd
   * @param List<MenuEntity> rsListTopMenu
   * @param List<MenuByRoleEntity> rsListAvailableMenuByRole
 	 */
  private GetListAvailableMenuResponseDto(
    NameByLanguageRepository nameByLanguageRepository,
    String nationCd,
    List<MenuEntity> rsListTopMenu,
    List<MenuByRoleEntity> rsListAvailableMenuByRole
  ) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<MenuEntity> rsListAvailableMenus = new ArrayList<>();

    for(MenuEntity rsMenu : rsListTopMenu) {
      for(MenuByRoleEntity rsMenuByRole : rsListAvailableMenuByRole) {
        if(rsMenu.getMenuNo().equals(rsMenuByRole.getMenu().getMenuNo())) rsListAvailableMenus.add(rsMenu);
      }
    }

    this.listAvailableMenus = rsListAvailableMenus
      .stream()
      .map(rsAvailableMenus -> new AvailableMenuListItem(nameByLanguageRepository, nationCd, rsAvailableMenus, rsListAvailableMenuByRole))
      .collect(Collectors.toList());
  }

  /* 
   * 로그인 사용자의 역할에 따라 사용가능한 메뉴리스트 가져오기 성공 시
   *  
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param String nationCd
   * @param List<MenuEntity> rsListTopMenu
   * @param List<MenuByRoleEntity> rsListAvailableMenuByRole
   * @return ResponseEntity<GetListAvailableMenuResponseDto>
   */
  public static ResponseEntity<GetListAvailableMenuResponseDto> success(
    NameByLanguageRepository nameByLanguageRepository,
    String nationCd,
    List<MenuEntity> rsListTopMenu,
    List<MenuByRoleEntity> rsListAvailableMenuByRole
  ) {
    GetListAvailableMenuResponseDto result = new GetListAvailableMenuResponseDto(nameByLanguageRepository, nationCd, rsListTopMenu, rsListAvailableMenuByRole);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

 	/*
   * 로그인 사용자의 역할에 따라 사용가능한 메뉴리스트 가져오기 실패 시(패스워드 입력 오류인 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> availableTopMenu() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_AVAILABLE_TOPMENU, ResponseMessage.NOT_EXISTED_AVAILABLE_TOPMENU);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
