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
import com.withtec.pfsms.dto.request.auth.PostSignInRequestDto;
import com.withtec.pfsms.dto.response.auth.PostEnvironmentSettingResponseDto;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.dto.response.auth.GetProfileResponseDto;
import com.withtec.pfsms.dto.response.auth.PatchChangePasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PutFindPasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PostSignInResponseDto;
import com.withtec.pfsms.dto.response.auth.PutSignOutResponseDto;
import com.withtec.pfsms.service.AuthService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Tag(name = "Auth-Controller", description = "인증관련 API 엔드포인트")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  
 	/**
   * 로그인
	 * 
	 * @param PostSignInRequestDto requestBody
	 * @param HttpServletRequest  request
	 * @return ResponseEntity<? super PostSignInResponseDto>
	 */
  @PostMapping("/sign-in")
  @Operation(summary = "로그인", description = "사이트 로그인 처리를 합니다.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "로그인 성공", content = {@Content(schema = @Schema(implementation = PostSignInResponseDto.class))}),
    @ApiResponse(responseCode = "500", description = "Database Error", content = {@Content(schema = @Schema(implementation = ResponseDto.class))}),
  })
  public ResponseEntity<? super PostSignInResponseDto> signIn(
    @RequestBody @Valid @Schema(implementation = PostSignInRequestDto.class) PostSignInRequestDto requestBody,
    HttpServletRequest request
  ) {
    ResponseEntity<? super PostSignInResponseDto> response = authService.signIn(requestBody, request);
    return response;
  }

 	/**
   * 사용자 프로필 가져오기
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetProfileResponseDto>
	 */
  @Hidden
  @GetMapping("/profile")
  @Operation(summary = "사용자 프로필 가져오기", description = "로그인 사용자의 프로파일을 가져옵니다.")
  @ApiResponse(responseCode = "200", description = "사용자 프로필 가져오기 성공")
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
  @Operation(summary = "로그아웃", description = "사이트 로그아웃 처리를 합니다.")
  @ApiResponse(responseCode = "200", description = "로그아웃 성공")
  public ResponseEntity<? super PutSignOutResponseDto> signOut(
    @AuthenticationPrincipal UserDetails userDetail,
    HttpServletRequest request
  ) {
      ResponseEntity<? super PutSignOutResponseDto> response = authService.signOut(userDetail, request);
      return response;
  }

 	/*
   * 패스워드 찾기
	 * 
	 * @param String loginId
	 * @return ResponseEntity<? super PutFindPasswordResponseDto>
	*/
  @Hidden
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
  @Hidden
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
  @Hidden
  @PostMapping("/environment")
  @Operation(summary = "사용자 환경설정 가져오기", description = "로그인 사용자의 환경설정 내역을 가져옵니다.")
  @ApiResponse(responseCode = "200", description = "사용자 환경설정 내역 가져오기 성공")
  public ResponseEntity<? super PostEnvironmentSettingResponseDto> getEnvironmentSetting(
    @RequestBody @Valid @Schema(implementation = PostEnvironmentSettingRequestDto.class) PostEnvironmentSettingRequestDto requestBody
  ) {
    ResponseEntity<? super PostEnvironmentSettingResponseDto> response = authService.getEnvironmentSetting(requestBody);
    return response;
  }
}