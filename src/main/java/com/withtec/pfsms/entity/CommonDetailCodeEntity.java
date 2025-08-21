/*------------------------------------------------------------------------------
 * NAME : CommonDetailCodeEntity.java
 * DESC : 시스템 공통상세코드 엔티티
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
import com.withtec.pfsms.entity.primaryKey.CommonDetailCodePK;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tsy_cmmn_dtl_code")
@Table(name = "tsy_cmmn_dtl_code")
@IdClass(CommonDetailCodePK.class)
public class CommonDetailCodeEntity extends BaseTimeEntity {
  
  @Id
  @Column(name = "CMMN_CD", nullable = false)
  private String cmmnCd;                                // 공통코드

  @Id
  @Column(name = "CMMN_DTL_CD", nullable = false)
  private String cmmnDtlCd;                             // 공통상세코드

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CMMN_CD", referencedColumnName = "CMMN_CD", insertable = false, updatable = false)
  private CommonCodeEntity rsCommonCode;

  @Column(name = "REFRN_1_CNTNT", nullable = true)
  private String refrn1Cntnt;                           // 참조1내용

  @Column(name = "REFRN_2_CNTNT", nullable = true)
  private String refrn2Cntnt;                           // 참조2내용

  @Column(name = "REFRN_3_CNTNT", nullable = true)
  private String refrn3Cntnt;                           // 참조3내용

  @Column(name = "REFRN_4_CNTNT", nullable = true)
  private String refrn4Cntnt;                           // 참조4내용

  @Column(name = "REFRN_5_CNTNT", nullable = true)
  private String refrn5Cntnt;                           // 참조5내용

  @Column(name = "RM_CNTNT", nullable = true)
  private String rmCntnt;                               // 비고내용

  @Column(name = "INDICT_ORDR", nullable = true)
  private String indictOrdr;                            // 표시순서

  @Column(name = "UPPER_CMMN_DTL_CD", nullable = true)
  private String upperCmmnDtlCd;                        // 상위공통상세코드

  @Column(name = "USE_YN", nullable = true)
  private String useYn;                                 // 사용여부

  @Embedded
  private BaseWriteEntity writer;
}
