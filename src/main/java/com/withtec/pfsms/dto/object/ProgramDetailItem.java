/*------------------------------------------------------------------------------
 * NAME : ProgramDetailItem.java
 * DESC : 메뉴 상세내역의 프로그램정보 항목 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/08  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.object;

import com.withtec.pfsms.entity.ProgramEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDetailItem {

  private String progrmCd;
  private String screenId;
  private String screenNm;
  
	/*
   * 생성자
	 * 
   * @param ProgramEntity rsProgram
 	 */
  private ProgramDetailItem(
    ProgramEntity rsProgram
  ) {
    if(rsProgram == null) {

      this.progrmCd = "";
      this.screenId = "";
      this.screenNm = "";
    } else {

      this.progrmCd = rsProgram.getProgrmCd();
      this.screenId = rsProgram.getScreenId();
      this.screenNm = rsProgram.getScreenNm();
    }
  }

	/*
   * 프로그램 정보 가져오기
	 * 
   * @param ProgramEntity rsProgram
   * @return ProgramDetailItem rsProgramDtl
	*/
  public static ProgramDetailItem getProgrmDtl(
    ProgramEntity rsProgram
  ) {
    
    ProgramDetailItem rsProgramDtl = new ProgramDetailItem(rsProgram);
    return rsProgramDtl;
  }
}
