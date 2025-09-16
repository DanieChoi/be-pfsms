/*------------------------------------------------------------------------------
 * NAME : CommonDetailCodePK.java
 * DESC : 공통 상세코드 테이블 PK
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
package com.withtec.pfsms.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class CommonDetailCodePK implements Serializable {
  
  @Column(name = "CMMN_CD")
  private String cmmnCd;

  @Column(name = "CMMN_DTL_CD")
  private String cmmnDtlCd;

	/*
   * 생성자
	 * 
   * @param String cmmnCd
   * @param String cmmnDtlCd
 	 */
  public CommonDetailCodePK(String cmmnCd, String cmmnDtlCd) {
    this.cmmnCd = cmmnCd;
    this.cmmnDtlCd = cmmnDtlCd;
  }
}
