/*------------------------------------------------------------------------------
 * NAME : CommonCodeListItem.java
 * DESC : 시스템 공통코드 리스트 항목 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/11  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.withtec.pfsms.common.Constants;
import com.withtec.pfsms.entity.CommonCodeEntity;
import com.withtec.pfsms.entity.NameByLanguageEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonCodeListItem {
  
  private String cmmnCd;                                // 공통코드
  private List<NameByLanguageListItem> cmmnNm;          // 언어별 공통코드명리스트
  private String rmCntnt;                               // 비고내용
  private String cdLen;                                 // 상세코드길이
  private String indictOrdr;                            // 코드 표시순서
  private String upperCmmnCd;                           // 상위 공통코드
  private String useYn;                                 // 사용여부

  /*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param CommonCodeEntity rsCommonCode
 	 */
  private CommonCodeListItem(
    NameByLanguageRepository nameByLanguageRepository,
    CommonCodeEntity rsCommonCode
  ) {
    this.cmmnCd = rsCommonCode.getCmmnCd();
    List<NameByLanguageEntity> rsNameByLangList = nameByLanguageRepository.findByNmClCdAndNmCd(Constants.CMMN_CODE_NAME, rsCommonCode.getCmmnCd());
    this.cmmnNm = NameByLanguageListItem.getMenuNameByLang(rsNameByLangList);
    this.rmCntnt = rsCommonCode.getRmCntnt();
    this.cdLen = rsCommonCode.getCdLen();
    this.indictOrdr = rsCommonCode.getIndictOrdr();
    this.upperCmmnCd = rsCommonCode.getUpperCmmnCd();
    this.useYn = rsCommonCode.getUseYn();
  }

	/*
   * 공통코드 가져오기
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param List<CommonCodeEntity> rsCommonCodeList
   * @return List<CommonCodeListItem> commonCodeList
	*/
  public static List<CommonCodeListItem> getCommonCodeList(
    NameByLanguageRepository nameByLanguageRepository,
    List<CommonCodeEntity> rsCommonCodeList
  ) {
    
    List<CommonCodeListItem> commonCodeList = new ArrayList<>();

    for(CommonCodeEntity rsCommonCode : rsCommonCodeList) {
      CommonCodeListItem commonCodeItem = new CommonCodeListItem(nameByLanguageRepository, rsCommonCode);
      commonCodeList.add(commonCodeItem);
    }

    return commonCodeList;
  }
}
