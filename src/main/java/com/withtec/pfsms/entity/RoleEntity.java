/*------------------------------------------------------------------------------
 * NAME : RoleEntity.java
 * DESC : 시스템 역할 엔티티
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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tsy_role")
@Table(name = "tsy_role")
public class RoleEntity extends BaseTimeEntity{
  
  @Id
  @Column(name = "ROLE_CD", nullable = false)
  private String roleCd;                                // 역할 코드

  @Column(name = "ROLE_NM", nullable = true)
  private String roleNm;                                // 역할 명

  @Column(name = "ROLE_CNTNT", nullable = true)
  private String roleCntnt;                             // 역할 설명

  @Column(name = "USE_YN", nullable = false)
  private String useYn;                                 // 사용여부

  @Column(name = "CTI_CONN_YN", nullable = false)
  private String ctiConnYn;                             // CTI 로그인 여부

  @Embedded
  private BaseWriteEntity writer;
}
