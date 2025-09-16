/*------------------------------------------------------------------------------
 * NAME : AuthService.java
 * DESC : 인증관련 요청에 대한 서비스 인터페이스
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 * 2025/07/15  최상원                       로그아웃, 패스워드 찾기, 패스워드 변경 추가
 * 2025/08/18  최상원                       사용자 프로필 가져오기 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.withtec.pfsms.dto.request.auth.PatchChangePasswordRequestDto;
import com.withtec.pfsms.dto.request.auth.PostEnvironmentSettingRequestDto;
import com.withtec.pfsms.dto.request.auth.PostSignInRequestDto;
import com.withtec.pfsms.dto.response.auth.PostEnvironmentSettingResponseDto;
import com.withtec.pfsms.dto.response.auth.GetProfileResponseDto;
import com.withtec.pfsms.dto.response.auth.PatchChangePasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PutFindPasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PostSignInResponseDto;
import com.withtec.pfsms.dto.response.auth.PutSignOutResponseDto;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
  
 	/*
   * 로그인
	 * 
	 * @param PutSignInRequestDto requestBody
	 * @param HttpServletRequest  request
	 * @return ResponseEntity<? super PutSignInResponseDto>
	*/
  ResponseEntity<? super PostSignInResponseDto> signIn(PostSignInRequestDto requestBody, HttpServletRequest  request);

 	/*
   * 사용자 프로필 가져오기
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetProfileResponseDto>
	*/
  ResponseEntity<? super GetProfileResponseDto> getProfile(UserDetails rsUserDetails);

 	/*
   * 로그아웃
	 * 
	 * @param UserDetails ud
	 * @param HttpServletRequest  hr
	 * @return ResponseEntity<? super PutSignOutResponseDto>
	*/
	ResponseEntity<? super PutSignOutResponseDto> signOut(UserDetails ud, HttpServletRequest  hr);

 	/*
   * 패스워드 찾기
	 * 
	 * @param String loginId
	 * @return ResponseEntity<? super PutFindPasswordResponseDto>
	*/
	ResponseEntity<? super PutFindPasswordResponseDto> findPassword(String loginId);

 	/*
   * 패스워드 변경
	 * 
	 * @param PatchChangePasswordRequestDto requestBody
	 * @return ResponseEntity<? super PatchChangePasswordResponseDto>
	*/
	ResponseEntity<? super PatchChangePasswordResponseDto> changePassword(PatchChangePasswordRequestDto requestBody);

  /*
   *  사용자별 환경설정 가져오기
   *
   *  @param PostEnvironmentSettingRequestDto requestBody 전달 개체 DTO
   *  @return ResponseEntity<? super GetEnvironmentSettingResponseDto>
   */
	ResponseEntity<? super PostEnvironmentSettingResponseDto> getEnvironmentSetting(PostEnvironmentSettingRequestDto requestBody);
}
