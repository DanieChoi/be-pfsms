/*------------------------------------------------------------------------------
 * NAME : RoleMenuId.java
 * DESC : 역할별 메뉴 테이블 PK
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
public class RoleMenuPK implements Serializable {
 
  @Column(name = "ROLE_CD")
  private String roleCd;

  @Column(name = "MENU_NO")
  private String menuNo;

	/*
   * 생성자
	 * 
   * @param String roleCd
   * @param String menuNo
 	 */
  public RoleMenuPK(String roleCd, String menuNo) {
    this.roleCd = roleCd;
    this.menuNo = menuNo;
  }
}
