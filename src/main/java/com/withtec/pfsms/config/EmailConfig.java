/*------------------------------------------------------------------------------
 * NAME : EmailConfig.java
 * DESC : Email 설정을 정의
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/15  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.port}")
  private int port;

  @Value("${spring.mail.username}")
  private String id;

  @Value("${spring.mail.password}")
  private String password;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  private boolean auth;

  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  private boolean starttlsEnable;

  @Value("${spring.mail.properties.mail.smtp.starttls.required}")
  private boolean starttlsRequired;

  @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
  private int connectionTimeout;

  @Value("${spring.mail.properties.mail.smtp.timeout}")
  private int timeout;

  @Value("${spring.mail.properties.mail.smtp.writetimeout}")
  private int writeTimeout;

  @Bean
  public JavaMailSender javaMalService() {

    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(host);
    javaMailSender.setUsername(id);
    javaMailSender.setPassword(password);
    javaMailSender.setPort(port);
    javaMailSender.setJavaMailProperties(getMailProperties());
    javaMailSender.setDefaultEncoding("UTF-8");

    return javaMailSender;
  }

  private Properties getMailProperties() {
    Properties pt = new Properties();
    //pt.put("mail.smtp.socketFactory.port", socketPort);
    pt.put("mail.smtp.auth", auth);
    pt.put("mail.smtp.starttls.enable", starttlsEnable);
    pt.put("mail.smtp.starttls.required", starttlsRequired);
    //pt.put("mail.smtp.socketFactory.fallback",fallback);
    pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    return pt;
  }
}
