/*------------------------------------------------------------------------------
 * NAME : MailService.java
 * DESC : 메일 발송 관련 서비스 인터페이스
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
package com.withtec.pfsms.service;

public interface MailService {
  
 	/*
   * 이메일 인증 및 중복 로직
	 * 
	 * @param String to
	 * @param String ePw
	 * @return String
	*/
  String sendCertificationMessage(String to, String ePw) throws Exception;

 	/*
   * 비밀번호 찾기
	 * 
	 * @param String to
	 * @param String nickname
	 * @return String
	*/
  String findPasswordMessage(String to, String nickname) throws Exception;

}
