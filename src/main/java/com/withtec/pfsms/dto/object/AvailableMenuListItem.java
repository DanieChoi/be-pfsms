/*------------------------------------------------------------------------------
 * NAME : AvailableMenuListItem.java
 * DESC : 로그인 사용자에 대한 사용가능한 메뉴리스트 항목 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/28  최상원                       초기작성
 * 2025/08/01  최상원                       menuIconUrl(메뉴 아이콘경로)/menuHref(메뉴접근경로) 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.withtec.pfsms.common.Constants;
import com.withtec.pfsms.entity.MenuByRoleEntity;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.entity.NameByLanguageEntity;
import com.withtec.pfsms.entity.primaryKey.LanguageNamePK;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableMenuListItem {
  
  private String menuNo;                        // 메뉴번호
  private String menuNm;                        // 메뉴명
  private String upperMenuNo;                   // 상위메뉴번호
  private String menuLevelCd;                   // 메뉴 수준코드
  private String menuSeCd;                      // 메뉴 구분코드
  private String menuIconUrl;                   // 메뉴 아이콘 경로
  private String menuHref;                      // 메뉴 접근 경로
  private List<AvailableMenuListItem> subMenu;  // 하위메뉴

	/*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param String nationCd
   * @param MenuEntity rsAvailableMenus
   * @param List<MenuByRoleEntity> rsListAvailableMenuByRole
 	 */
  public AvailableMenuListItem(
    NameByLanguageRepository nameByLanguageRepository,
    String nationCd,
    MenuEntity rsAvailableMenu,
    List<MenuByRoleEntity> rsListAvailableMenuByRole
  ) {
    this.menuNo = rsAvailableMenu.getMenuNo();

    // 복합키 인스턴스 생성
    LanguageNamePK langNmPk = new LanguageNamePK(Constants.MENU_NAME, rsAvailableMenu.getMenuNo(), nationCd);
    Optional<NameByLanguageEntity> rsNameByLanguage = nameByLanguageRepository.findById(langNmPk);
    if (rsNameByLanguage.isEmpty()) {
      this.menuNm = "";
    } else {
      this.menuNm = rsNameByLanguage.get().getName();
    }

    this.upperMenuNo = rsAvailableMenu.getUpperMenu().getMenuNo();
    this.menuLevelCd = rsAvailableMenu.getMenuLevelCd();
    this.menuSeCd = rsAvailableMenu.getMenuSeCd();
    this.menuIconUrl = rsAvailableMenu.getMenuIconUrl();
    if(rsAvailableMenu.getProgram() == null) {
      this.menuHref = "";
    } else {
      this.menuHref = rsAvailableMenu.getProgram().getProgrmFnctSeCd() + '/' + rsAvailableMenu.getProgram().getScreenId();
    }

    List<MenuEntity> rsListAvailableMenus = new ArrayList<>();
    for(MenuEntity rsTempMenu : rsAvailableMenu.getSubMenu()) {
      for(MenuByRoleEntity rsMenuByRole : rsListAvailableMenuByRole) {
        if(rsTempMenu.getMenuNo().equals(rsMenuByRole.getMenu().getMenuNo())) rsListAvailableMenus.add(rsTempMenu);
      }
    }

    this.subMenu = rsListAvailableMenus
      .stream()
      .filter(rsAvailableMenus -> rsAvailableMenus.getIndictYn().equals("Y"))
      .map(rsAvailableMenus -> new AvailableMenuListItem(nameByLanguageRepository, nationCd, rsAvailableMenus, rsListAvailableMenuByRole))
      .collect(Collectors.toList());
  }
}
