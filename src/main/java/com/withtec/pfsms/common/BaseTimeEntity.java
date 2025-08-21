/*------------------------------------------------------------------------------
 * NAME : BaseTimeEntity.java
 * DESC : 엔티티 공통항목 정의
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.common;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseTimeEntity {

  @CreatedDate
  @Column(name = "reg_dttm", updatable = false)
  private Instant regDttm;

  @LastModifiedDate
  @Column(name = "updt_dttm")
  private Instant updDttm;

  @PrePersist
  public void onPrePersist() {
    this.regDttm = Instant.now();
    this.updDttm = this.regDttm;
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updDttm = Instant.now();
  }
}
