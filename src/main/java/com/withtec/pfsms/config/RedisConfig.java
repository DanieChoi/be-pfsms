/*------------------------------------------------------------------------------
 * NAME : RedisConfig.java
 * DESC : Redis 환경설정
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/07  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableRedisRepositories
public class RedisConfig {
  
  @Value("${spring.data.redis.host}")
  private String redisHost;

  @Value("${spring.data.redis.port}")
  private int redisPort;

  // @Value("${spring.data.redis.password}")
  // private String redisPassword;

  @Bean
  @Primary
  LettuceConnectionFactory connectionFactory() { return createConnectionFactoryWith(1); }
  @Bean
  public RedisMessageListenerContainer redisMessageListener(RedisConnectionFactory connectionFactory) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    return container;
  }
  @Bean
  @Primary
  RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    // redis template에 connection factory 연결
    redisTemplate.setConnectionFactory(connectionFactory);

    // 일반적인 key:value의 경우 시리얼라이저
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());

    // Hash를 사용할 경우 시리얼라이저
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new StringRedisSerializer());

    return redisTemplate;
  }

  //Redis 연결을 위한 'Connection' 생성합니다.
  public LettuceConnectionFactory createConnectionFactoryWith(int index) {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(redisHost);
    redisStandaloneConfiguration.setPort(redisPort);
    // redisStandaloneConfiguration.setPassword(redisPassword);
    redisStandaloneConfiguration.setDatabase(index);

    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }
}
