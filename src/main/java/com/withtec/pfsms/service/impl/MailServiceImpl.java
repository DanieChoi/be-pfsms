/*------------------------------------------------------------------------------
 * NAME : MailServiceImpl.java
 * DESC : 메일 발송 관련 서비스 구현체
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
package com.withtec.pfsms.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.withtec.pfsms.dto.object.MailDto;
import com.withtec.pfsms.service.MailService;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
  
  private final JavaMailSender emailSender;

  public String generateEmailConfirmationToken() {
    return createKey();
  }

  @Value("${spring.mail.username}")
  private String from; //보내는 사람

 	/*
   * 이메일 인증키 생성
	 * 
	 * @return String
	*/
  public static String createKey() {
    StringBuffer key = new StringBuffer();
    Random rnd = new Random();

    //100000부터 899999사이의 난수 생성
    int number = rnd.nextInt(899999) + 100000;
    key.append(number);

    //영문 + 숫자 조합의 난수 생성
    // for (int i = 0; i < 8; i++) { // 인증코드 8자리
    //   int index = rnd.nextInt(3); // 0~2 까지 랜덤

    //   switch (index) {
    //     case 0:
    //       key.append((char) ((int) (rnd.nextInt(26)) + 97));
    //       //  a~z  (ex. 1+97=98 => (char)98 = 'b')
    //       break;
    //     case 1:
    //       key.append((char) ((int) (rnd.nextInt(26)) + 65));
    //       //  A~Z
    //       break;
    //     case 2:
    //       key.append((rnd.nextInt(10)));
    //       // 0~9
    //       break;
    //   }
    // }

    return key.toString();
  }

  private MimeMessage createCertificationMessage(String to, String ePw) throws Exception {

    MimeMessage message = emailSender.createMimeMessage();

    message.addRecipients(RecipientType.TO, to);
    message.setSubject("이메일 인증번호 입니다.");

    String bodyMessage = "";

    message.setText(bodyMessage, "utf-8", "html");
    message.setFrom(new InternetAddress(from, "상조사명"));

    return message;
  }

 	/*
   * 이메일 발송
	 * 
	 * @param MailDto mailDto
	 * @return void
	*/
  public void sendMail(MailDto mailDto) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(mailDto.getToAddress());
    mailMessage.setSubject(mailDto.getTitle());
    mailMessage.setText(mailDto.getMessage());
    mailMessage.setFrom(from);
    mailMessage.setReplyTo(from);

    emailSender.send(mailMessage);
  }

 	/*
   * 이메일 인증 및 중복 로직
	 * 
	 * @param String to
	 * @param String ePw
	 * @return String
	*/
  @Override
  public String sendCertificationMessage(String to, String ePw) throws Exception {
    @NonNull
    MimeMessage message = createCertificationMessage(to, ePw);

    try{
      emailSender.send(message);
    }catch(MailException es){
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
    return ePw;
  }

 	/*
   * 비밀번호 찾기 웹메일
	 * 
	 * @param String to
	 * @param String nickname
	 * @return String
	*/
  @Override
  public String findPasswordMessage(String to, String nickname) throws Exception {

    @NonNull
    MimeMessage message = createCertificationMessage(to, nickname);

    try{
      emailSender.send(message);
    }catch(MailException es){
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
    return nickname;
  }
}
