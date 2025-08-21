/*------------------------------------------------------------------------------
 * NAME : CommonCodeEntity.java
 * DESC : 시스템 공통코드 엔티티
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
package com.withtec.pfsms.entity;

import com.withtec.pfsms.common.BaseTimeEntity;
import com.withtec.pfsms.common.BaseWriteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tsy_cmmn_code")
@Table(name = "tsy_cmmn_code")
public class CommonCodeEntity extends BaseTimeEntity {
  
  @Id
  @Column(name = "CMMN_CD", nullable = false)
  private String cmmnCd;                                // 공통코드

  @Column(name = "RM_CNTNT", nullable = true)
  private String rmCntnt;                               // 비고내용

  @Column(name = "CD_LEN", nullable = true)
  private String cdLen;                                 // 상세코드길이

  @Column(name = "INDICT_ORDR", nullable = true)
  private String indictOrdr;                            // 코드 표시순서

  @Column(name = "UPPER_CMMN_CD", nullable = true)
  private String upperCmmnCd;                           // 상위 공통코드

  @Column(name = "USE_YN", nullable = false)
  private String useYn;                                 // 사용여부

  @Embedded
  private BaseWriteEntity writer;
}
