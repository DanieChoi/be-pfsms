/*------------------------------------------------------------------------------
 * NAME : CommonDetailCodeListItem.java
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
import com.withtec.pfsms.entity.CommonDetailCodeEntity;
import com.withtec.pfsms.entity.NameByLanguageEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonDetailCodeListItem {
  
  private String cmmnCd;                                // 공통코드
  private String cmmnDtlCd;                             // 공통상세코드
  private List<NameByLanguageListItem> cmmnDtlNm;       // 언어별 공통상세코드명리스트
  private String refrn1Cntnt;                           // 참조1내용
  private String refrn2Cntnt;                           // 참조2내용
  private String refrn3Cntnt;                           // 참조3내용
  private String refrn4Cntnt;                           // 참조4내용
  private String refrn5Cntnt;                           // 참조5내용
  private String rmCntnt;                               // 비고내용
  private String indictOrdr;                            // 표시순서
  private String upperCmmnDtlCd;                        // 상위공통상세코드
  private String useYn;                                 // 사용여부

  /*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param CommonDetailCodeEntity rsCommonDetailCode
 	 */
  private CommonDetailCodeListItem(
    NameByLanguageRepository nameByLanguageRepository,
    CommonDetailCodeEntity rsCommonDetailCode
  ) {
    this.cmmnCd = rsCommonDetailCode.getCmmnCd();
    this.cmmnDtlCd = rsCommonDetailCode.getCmmnDtlCd();
    List<NameByLanguageEntity> rsNameByLangList = nameByLanguageRepository.findByNmClCdAndNmCd(Constants.CMMN_DTL_CODE_NAME, rsCommonDetailCode.getCmmnCd() + rsCommonDetailCode.getCmmnDtlCd());
    this.cmmnDtlNm = NameByLanguageListItem.getMenuNameByLang(rsNameByLangList);
    this.refrn1Cntnt = rsCommonDetailCode.getRefrn1Cntnt();
    this.refrn2Cntnt = rsCommonDetailCode.getRefrn2Cntnt();
    this.refrn3Cntnt = rsCommonDetailCode.getRefrn3Cntnt();
    this.refrn4Cntnt = rsCommonDetailCode.getRefrn4Cntnt();
    this.refrn5Cntnt = rsCommonDetailCode.getRefrn5Cntnt();
    this.rmCntnt = rsCommonDetailCode.getRmCntnt();
    this.indictOrdr = rsCommonDetailCode.getIndictOrdr();
    this.upperCmmnDtlCd = rsCommonDetailCode.getUpperCmmnDtlCd();
    this.useYn = rsCommonDetailCode.getUseYn();
  }

	/*
   * 공통상세코드 가져오기
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param List<CommonDetailCodeEntity> rsCommonDetailCodeList
   * @return List<CommonDetailCodeListItem> commonDetailCodeList
	*/
  public static List<CommonDetailCodeListItem> getCommonDetailCodeList(
    NameByLanguageRepository nameByLanguageRepository,
    List<CommonDetailCodeEntity> rsCommonDetailCodeList
  ) {
    
    List<CommonDetailCodeListItem> commonDetailCodeList = new ArrayList<>();

    for(CommonDetailCodeEntity rsCommonDetailCode : rsCommonDetailCodeList) {
      CommonDetailCodeListItem commonDetailCodeItem = new CommonDetailCodeListItem(nameByLanguageRepository, rsCommonDetailCode);
      commonDetailCodeList.add(commonDetailCodeItem);
    }

    return commonDetailCodeList;
  }

}
