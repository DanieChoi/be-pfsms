/*------------------------------------------------------------------------------
 * NAME : AuthController.java
 * DESC : 인증관련 요청이 진입하는 End Point
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
package com.withtec.pfsms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withtec.pfsms.dto.request.auth.PatchChangePasswordRequestDto;
import com.withtec.pfsms.dto.request.auth.PostEnvironmentSettingRequestDto;
import com.withtec.pfsms.dto.request.auth.PutSignInRequestDto;
import com.withtec.pfsms.dto.response.auth.PostEnvironmentSettingResponseDto;
import com.withtec.pfsms.dto.response.auth.GetProfileResponseDto;
import com.withtec.pfsms.dto.response.auth.PatchChangePasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PutFindPasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PutSignInResponseDto;
import com.withtec.pfsms.dto.response.auth.PutSignOutResponseDto;
import com.withtec.pfsms.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  
 	/*
   * 로그인
	 * 
	 * @param PutSignInRequestDto requestBody
	 * @param HttpServletRequest  request
	 * @return ResponseEntity<? super PutSignInResponseDto>
	*/
  @PostMapping("/sign-in")
  public ResponseEntity<? super PutSignInResponseDto> signIn(
    @RequestBody @Valid PutSignInRequestDto requestBody,
    HttpServletRequest request
  ) {
    ResponseEntity<? super PutSignInResponseDto> response = authService.signIn(requestBody, request);
    return response;
  }

 	/*
   * 사용자 프로필 가져오기
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetProfileResponseDto>
	*/
  @GetMapping("/profile")
  public ResponseEntity<? super GetProfileResponseDto> getProfile(
    @AuthenticationPrincipal UserDetails rsUserDetails
  ) {
    ResponseEntity<? super GetProfileResponseDto> response = authService.getProfile(rsUserDetails);
    return response;
  }

 	/*
   * 로그아웃
	 * 
	 * @param UserDetails userDetail
	 * @param HttpServletRequest  request
	 * @return ResponseEntity<? super PutSignOutResponseDto>
	*/
  @PutMapping("/sign-out")
  public ResponseEntity<? super PutSignOutResponseDto> signOut(
    @AuthenticationPrincipal UserDetails ud,
    HttpServletRequest hr
  ) {
      ResponseEntity<? super PutSignOutResponseDto> response = authService.signOut(ud, hr);
      return response;
  }

 	/*
   * 패스워드 찾기
	 * 
	 * @param String loginId
	 * @return ResponseEntity<? super PutFindPasswordResponseDto>
	*/
  @PutMapping("/find-password")
  public ResponseEntity<? super PutFindPasswordResponseDto> findPassword(
    @RequestParam String loginId
  ) {
    ResponseEntity<? super PutFindPasswordResponseDto> response = authService.findPassword(loginId);
    return response;
  }

 	/*
   * 패스워드 변경
	 * 
	 * @param PatchChangePasswordRequestDto requestBody
	 * @return ResponseEntity<? super PatchChangePasswordResponseDto>
	*/
  @PatchMapping("/change-password")
  public ResponseEntity<? super PatchChangePasswordResponseDto> changePassword(
    @RequestBody @Valid PatchChangePasswordRequestDto requestBody
  ) {
    ResponseEntity<? super PatchChangePasswordResponseDto> response = authService.changePassword(requestBody);
    return response;
  }

  /*
   *  사용자별 환경설정 가져오기
   *
   *  @param PostEnvironmentSettingRequestDto requestBody 전달 개체 DTO
   *  @return ResponseEntity<? super GetEnvironmentSettingResponseDto>
   */
  @PostMapping("/environment")
  public ResponseEntity<? super PostEnvironmentSettingResponseDto> getEnvironmentSetting(
    @RequestBody @Valid PostEnvironmentSettingRequestDto requestBody
  ) {
    ResponseEntity<? super PostEnvironmentSettingResponseDto> response = authService.getEnvironmentSetting(requestBody);
    return response;
  }
}