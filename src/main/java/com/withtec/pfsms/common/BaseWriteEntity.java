/*------------------------------------------------------------------------------
 * NAME : BaseWriteEntity.java
 * DESC : 엔티티 등록 시 공통항목 정의
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
package com.withtec.pfsms.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BaseWriteEntity {
  @Column(name = "REGTR_ID", nullable = false)
  private String regtrId;                               // 등록자 ID

  @Column(name = "REGTR_IP", nullable = false)
  private String regtrIp;                               // 등록자 IP주소

  @Column(name = "UPDUSR_ID", nullable = false)
  private String updusrId;                              // 최종 수정자 ID

  @Column(name = "UPDUSR_IP", nullable = false)
  private String updusrIp;                              // 최종 수정자 IP주소  
}
