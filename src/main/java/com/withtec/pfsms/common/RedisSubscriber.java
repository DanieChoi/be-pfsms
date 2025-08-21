/*------------------------------------------------------------------------------
 * NAME : RedisSubscriber.java
 * DESC : 채널을 구독하는 Subscriber 객체
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
package com.withtec.pfsms.common;

import java.io.IOException;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.withtec.pfsms.dto.object.NotificationDto;
import com.withtec.pfsms.service.SseEmitterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class RedisSubscriber implements MessageListener {
  
  private final ObjectMapper objectMapper;
  private final SseEmitterService sseEmitterService;
  
  @Override
  public void onMessage(Message message, @Nullable byte[] pattern) {
    try {

      String channel = new String(message.getChannel());

      NotificationDto notificationDto = objectMapper.readValue(message.getBody(), NotificationDto.class);
      sseEmitterService.sendNotificationToClient(channel, notificationDto);

    } catch (IOException e) {
      log.error("IOException is occurred. ", e);
    }
  }  
}
