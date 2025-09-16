/*------------------------------------------------------------------------------
 * NAME : ProgramEntity.java
 * DESC : 시스템 프로그램 엔티티
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
@Entity(name = "tsy_program")
@Table(name = "tsy_program")
public class ProgramEntity extends BaseTimeEntity {

  @Id
  @Column(name = "PROGRM_CD", nullable = false)
  private String progrmCd;                              // 프로그램 코드

  @Column(name = "PROGRM_FNCT_SE_CD", nullable = true)
  private String progrmFnctSeCd;                        // 프로그램 기능구분코드

  @Column(name = "SCREEN_ID", nullable = false)
  private String screenId;                              // 화면아이디

  @Column(name = "SCREEN_NM", nullable = true)
  private String screenNm;                              // 화면명

  @Column(name = "USE_YN", nullable = false)
  private String useYn;                                 // 사용여부

  @Embedded
  private BaseWriteEntity writer;
}
