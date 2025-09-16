/*------------------------------------------------------------------------------
 * NAME : MenuByRoleEntity.java
 * DESC : 시스템 역할별 사용 가는 메뉴 엔티티
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

import java.util.List;

import com.withtec.pfsms.common.BaseTimeEntity;
import com.withtec.pfsms.common.BaseWriteEntity;
import com.withtec.pfsms.entity.primaryKey.RoleMenuPK;

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
@Entity(name = "tsy_menu_by_role")
@Table(name = "tsy_menu_by_role")
public class MenuByRoleEntity extends BaseTimeEntity {
  
  @EmbeddedId
  private RoleMenuPK id;

  @MapsId("roleCd")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ROLE_CD")
  private RoleEntity role;

  @MapsId("menuNo")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MENU_NO")
  private MenuEntity menu;

  @Column(name = "ERYY_BTN_INDICT_YN", nullable = true)
  private String eryyBtnIndictYn;

  @Column(name = "NEW_BTN_INDICT_YN", nullable = true)
  private String newBtnIndictYn;

  @Column(name = "INQIRE_BTN_INDICT_YN", nullable = true)
  private String inqireBtnIndictYn;

  @Column(name = "SAVE_BTN_INDICT_YN", nullable = true)
  private String saveBtnIndictYn;

  @Column(name = "DELETE_BTN_INDICT_YN", nullable = true)
  private String deleteBtnIndictYn;

  @Column(name = "EXCEL_BTN_INDICT_YN", nullable = true)
  private String excelBtnIndictYn;

  @Column(name = "OUTPT_BTN_INDICT_YN", nullable = true)
  private String outptBtnIndictYn;

  @Column(name = "CNFIRM_BTN_INDICT_YN", nullable = true)
  private String cnfirmBtnIndictYn;

  @Column(name = "TMPR_1_BTN_INDICT_YN", nullable = true)
  private String tmpr1BtnIndictYn;

  @Column(name = "TMPR_2_BTN_INDICT_YN", nullable = true)
  private String tmpr2BtnIndictYn;

  @Column(name = "TMPR_3_BTN_INDICT_YN", nullable = true)
  private String tmpr3BtnIndictYn;

  @Column(name = "TMPR_1_BTN_NM", nullable = true)
  private String tmpr1BtnNn;

  @Column(name = "TMPR_2_BTN_NM", nullable = true)
  private String tmpr2BtnNn;

  @Column(name = "TMPR_3_BTN_NM", nullable = true)
  private String tmpr3BtnNn;

  @Column(name = "USE_YN", nullable = false)
  private String useYn;                                 // 사용여부

  @Embedded
  private BaseWriteEntity writer;


	/*
   * 생성자
	 * 
   * @param List<String> listAvailableItem
 	 */
  public MenuByRoleEntity(List<String> listAvailableItem) {
    this.eryyBtnIndictYn = listAvailableItem.get(0);
  }

  /* 
   * 로그인 사용자의 역할에 따라 사용가능한 메뉴리스트 가져오기 성공 시
   *  
   * @param List<MenuEntity> rsListTopMenu
   * @return ResponseEntity<GetListAvailableMenuResponseDto>
   */
}
