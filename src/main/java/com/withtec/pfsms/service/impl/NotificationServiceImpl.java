/*------------------------------------------------------------------------------
 * NAME : NotificationServiceImpl.java
 * DESC : 알림 이벤트 기능 구현체
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
package com.withtec.pfsms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.withtec.pfsms.service.NotificationService;
import com.withtec.pfsms.service.RedisMessageService;
import com.withtec.pfsms.service.SseEmitterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
  
  private final SseEmitterService sseEmitterService;
  private final RedisMessageService redisMessageService;

  /*
   *  알림 이벤트 구독
   *  
   *  @param String channelId   채널ID
   *  @param String loginId     로그인ID
   *  @return SseEmitter
   */
  @Override
  public SseEmitter subscribe(String channelId, String loginId) {
    String emitterKey = loginId + "_" + channelId;

    SseEmitter sseEmitter = sseEmitterService.createEmitter(emitterKey);
    sseEmitterService.send("Connected!!", emitterKey, sseEmitter);

    redisMessageService.subscribe(channelId, loginId);

    sseEmitter.onTimeout(() -> {
      log.info("Server Sent Event timed out : emiterKey={}, emitter={}", emitterKey, sseEmitter.toString());
      sseEmitter.complete();      
    });
    sseEmitter.onError(e -> {
      log.info("Server Sent Event error occurred : emiterKey={}, message={}", emitterKey, e.getMessage());
      sseEmitter.completeWithError(e);
      sseEmitterService.deleteEmitter(emitterKey);
    });
    sseEmitter.onCompletion(() -> {
      sseEmitterService.deleteEmitter(emitterKey);
      log.info("disconnected by completed Server Sent Event : emiterKey={}, emitter={}", emitterKey, sseEmitter.toString());
      // redisMessageService.removeSubscribe(tenantId);
    });

    // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방    
    // if (!lastEventId.isEmpty()) {
    //   Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(String.valueOf(userId));
    //   events.entrySet().stream()
    //         .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
    //         .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
    // }

    return sseEmitter;
  }

  /*
   *  알림 이벤트 전송
   *  
   *  @param PostRedisMessagePublishRequestDto requestBody   실시간 이벤트 발행 개체 DTO
   *  @return void
   */
  // @Override
  // public void publicNotification(PostRedisMessagePublishRequestDto requestBody) {
  //   redisMessageService.publish(requestBody.getTenantId(), requestBody.getNotification());
  // }
}
