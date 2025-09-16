/*------------------------------------------------------------------------------
 * NAME : NameByLanguageEntity.java
 * DESC : 언어별 이름 엔티티
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
import com.withtec.pfsms.entity.primaryKey.LanguageNamePK;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tsy_name_by_language")
@Table(name = "tsy_name_by_language")
@IdClass(LanguageNamePK.class)
public class NameByLanguageEntity extends BaseTimeEntity {
  
  @Id
  @Column(name = "NM_CL_CD", nullable = false)
  private String nmClCd;

  @Id
  @Column(name = "NM_CD", nullable = false)
  private String nmCd;

  @Id
  @Column(name = "LANG_CD", nullable = false)
  private String langCd;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "USE_YN", nullable = false)
  private String useYn;                                 // 사용여부

  @Embedded
  private BaseWriteEntity writer;
}
