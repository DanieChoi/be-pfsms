/*------------------------------------------------------------------------------
 * NAME : NotificationService.java
 * DESC : 알림 이벤트 기능
 * VER  : V1.0
 * PROJ : 웹 기반 PDS 구축 프로젝트
 * Copyright 2024 Dootawiz All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/04  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
  
  /*
   *  알림 이벤트 구독
   *  
   *  @param String channelId   채널ID
   *  @param String loginId     로그인ID
   *  @return SseEmitter
   */
  public SseEmitter subscribe(String channelId, String loginId);

  /*
   *  알림 이벤트 전송
   *  
   *  @param PostRedisMessagePublishRequestDto requestBody   실시간 이벤트 발행 개체 DTO
   *  @return void
   */
  // public void publicNotification(PostRedisMessagePublishRequestDto requestBody);
}
