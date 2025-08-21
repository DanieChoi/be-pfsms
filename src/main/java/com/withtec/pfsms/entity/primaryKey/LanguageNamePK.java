/*------------------------------------------------------------------------------
 * NAME : LanguageNameId.java
 * DESC : 언어별 이름 테이블 PK
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
package com.withtec.pfsms.entity.primaryKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class LanguageNamePK implements Serializable {
  
  @Column(name = "NM_CL_CD")
  private String nmClCd;

  @Column(name = "NM_CD")
  private String nmCd;

  @Column(name = "LANG_CD")
  private String langCd;

	/*
   * 생성자
	 * 
   * @param String nmClCd
   * @param String nmCd
   * @param String langCd
 	 */
  public LanguageNamePK(String nmClCd, String nmCd, String langCd) {
    this.nmClCd = nmClCd;
    this.nmCd = nmCd;
    this.langCd = langCd;
  }
}
