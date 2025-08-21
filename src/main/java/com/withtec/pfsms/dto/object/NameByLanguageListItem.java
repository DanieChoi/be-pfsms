/*------------------------------------------------------------------------------
 * NAME : nameByLangListItem.java
 * DESC : 언어별 항목이름 리스트 항목 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/05  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.withtec.pfsms.entity.NameByLanguageEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NameByLanguageListItem {

  private String langCd;
  private String itemNm;

	/*
   * 생성자
	 * 
   * @param NameByLanguageEntity rsNameByLanguage
 	 */
  private NameByLanguageListItem(
    NameByLanguageEntity rsNameByLanguage
  ) {
    this.langCd = rsNameByLanguage.getLangCd();
    this.itemNm = rsNameByLanguage.getName();
  }

	/*
   * 언어별 항목이름 가져오기
	 * 
   * @param List<NameByLanguageEntity> rsNameByLangList
   * @return List<NameByLanguageListItem> nameByLangList
	*/
  public static List<NameByLanguageListItem> getMenuNameByLang(
    List<NameByLanguageEntity> rsNameByLangList
  ) {
    
    List<NameByLanguageListItem> nameByLangList = new ArrayList<>();

    for(NameByLanguageEntity rsNameByLanguage : rsNameByLangList) {
      NameByLanguageListItem rsMenuNmByLang = new NameByLanguageListItem(rsNameByLanguage);
      nameByLangList.add(rsMenuNmByLang);
    }

    return nameByLangList;

  }
}
