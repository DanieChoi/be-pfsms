/*------------------------------------------------------------------------------
 * NAME : MenuEntity.java
 * DESC : 시스템 메뉴 엔티티
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/28  최상원                       초기작성
 * 2025/08/01  최상원                       menuIconUrl(메뉴 아이콘경로)/menuHref(메뉴접근경로) 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.entity;

import java.util.ArrayList;
import java.util.List;

import com.withtec.pfsms.common.BaseTimeEntity;
import com.withtec.pfsms.common.BaseWriteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tsy_menu")
@Table(name = "tsy_menu")
public class MenuEntity extends BaseTimeEntity {

  @Id
  @Column(name = "MENU_NO", nullable = false)
  private String menuNo;                                // 메뉴번호

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UPPER_MENU_NO")
  private MenuEntity upperMenu;                         // 상위메뉴
  
  @OneToOne
  @JoinColumn(name = "PROGRM_CD", nullable = true)
  private ProgramEntity program;                        // 프로그램 개체

  @Column(name = "MENU_ICON_URL", nullable = true)
  private String menuIconUrl;                           // 메뉴 아이콘경로

  @Column(name = "MENU_HREF", nullable = true)
  private String menuHref;                              // 메뉴 접근 경로

  @Column(name = "MENU_DC_CNTNT", nullable = true)
  private String menuDcCntnt;                           // 메뉴에 대한 설명

  @Column(name = "MENU_LEVEL_CD", nullable = true)
  private String menuLevelCd;                           // 메뉴 수준코드

  @Column(name = "MENU_SE_CD", nullable = true)
  private String menuSeCd;                              // 메뉴 구분코드

  @Column(name = "MENU_ORDR", nullable = true)
  private int menuOrdr;                                 // 메뉴 표시순서

  @Column(name = "MENU_LCLAS_CD", nullable = true)
  private String menuLclasCd;                           // 메뉴 대분류코드

  @Column(name = "MENU_MCLAS_CD", nullable = true)
  private String menuMclasCd;                           // 메뉴 중분류코드

  @Column(name = "MENU_SCLAS_CD", nullable = true)
  private String menuSclasCd;                           // 메뉴 소분류코드

  @Column(name = "MENU_DTLCLFC_CD", nullable = true)
  private String menuDtlClfcCd;                         // 메뉴 상세분류코드

  @Column(name = "INDICT_YN", nullable = false)
  private String indictYn;                              // 메뉴 표시여부

  @Embedded
  private BaseWriteEntity writer;
  
  //메뉴의 하위메뉴
  @OneToMany(mappedBy = "upperMenu")
  private List<MenuEntity> subMenu = new ArrayList<>();
}
