/*------------------------------------------------------------------------------
 * NAME : MenuTreeListItem.java
 * DESC : 시스템 메뉴관리의 메뉴트리 리스트 항목 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/05  최상원                       초기작성
 * 2025/08/08  최상원                       MenuListItem에서 MenuTreeListItem으로 변경
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class MenuTreeListItem {

  private String menuNo;
  private String menuNm;
  private List<MenuTreeListItem> subMenu;  // 하위메뉴
  
	/*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param String nationCd
   * @param MenuEntity rsTreeMenu
 	 */
  public MenuTreeListItem(
    NameByLanguageRepository nameByLanguageRepository,
    String nationCd,
    MenuEntity rsTreeMenu
  ) {

    this.menuNo = rsTreeMenu.getMenuNo();

    // 복합키 인스턴스 생성
    LanguageNamePK langNmPk = new LanguageNamePK("0003", rsTreeMenu.getMenuNo(), nationCd);
    Optional<NameByLanguageEntity> rsNameByLanguage = nameByLanguageRepository.findById(langNmPk);
    if (rsNameByLanguage.isEmpty()) {
      this.menuNm = "";
    } else {
      this.menuNm = rsNameByLanguage.get().getName();
    }

    List<MenuEntity> rsSubMenuList = new ArrayList<>();
    rsSubMenuList = rsTreeMenu.getSubMenu();

    this.subMenu = rsSubMenuList
      .stream()
      .map(rsSubMenu -> new MenuTreeListItem(nameByLanguageRepository, nationCd, rsSubMenu))
      .collect(Collectors.toList());
  }
}
