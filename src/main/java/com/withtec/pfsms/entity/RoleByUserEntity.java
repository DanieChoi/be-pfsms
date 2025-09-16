/*------------------------------------------------------------------------------
 * NAME : RoleByUserEntity.java
 * DESC : 사용자별 역할 엔티티
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
import com.withtec.pfsms.entity.primaryKey.UserRolePK;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tsy_role_by_user")
@Table(name = "tsy_role_by_user")
public class RoleByUserEntity extends BaseTimeEntity {
  
  @EmbeddedId
  private UserRolePK id;

  @MapsId("loginId")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LOGIN_ID")
  private UserEntity user;

  @MapsId("roleCd")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ROLE_CD")
  private RoleEntity role;

  @Column(name = "USE_YN", nullable = false)
  private String useYn;                                 // 사용여부

  @Embedded
  private BaseWriteEntity writer;
}
