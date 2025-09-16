/*------------------------------------------------------------------------------
 * NAME : UserRoleId.java
 * DESC : 사용자별 권한 테이블 PK
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
public class UserRolePK implements Serializable {
  
  @Column(name = "LOGIN_ID")
  private String loginId;

  @Column(name = "ROLE_CD")
  private String roleCd;

	/*
   * 생성자
	 * 
   * @param String loginId
   * @param String roleCd
 	 */
  public UserRolePK(String loginId, String roleCd) {
    this.loginId = loginId;
    this.roleCd = roleCd;
  }
}
