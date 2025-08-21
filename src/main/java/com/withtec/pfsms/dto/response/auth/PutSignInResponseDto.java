/*------------------------------------------------------------------------------
 * NAME : PutSignInResponseDto.java
 * DESC : 인증관련 API 호출 시 반환하는 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class PutSignInResponseDto extends ResponseDto {
  
  private String tokenKey;
  private String roleCd;
  private Long expiresIn;

 	/*
   * 생성자
	 * 
	 * @param String  token
	*/
  private PutSignInResponseDto(String token) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.tokenKey = token;
    this.roleCd = "MNU001";
    this.expiresIn = 3600L;
  }

 	/*
   * 로그인 성공 시
	 * 
	 * @param String  token
	 * @return ResponseEntity<PutSignInResponseDto>
	*/
  public static ResponseEntity<PutSignInResponseDto> success(String token) {
    PutSignInResponseDto result = new PutSignInResponseDto(token);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

 	/*
   * 로그인 실패 시(로그인 ID가 존재하지 않을 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> notExistLoginId() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_LOGINID, ResponseMessage.NOT_EXISTED_LOGINID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

 	/*
   * 로그인 실패 시(패스워드 입력 오류인 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> passwordError() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_LOGINID, ResponseMessage.NOT_EXISTED_LOGINID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

 	/*
   * 로그인 실패 시(패스워드 입력 오류가 3회이상인 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> passwordError3TimesOrMore() {
    ResponseDto result = new ResponseDto(ResponseCode.ERROR_3TIMES_OR_MORE, ResponseMessage.ERROR_3TIMES_OR_MORE);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

 	/*
   * 로그인 실패 시(패스워드 변경 주기 일자 초과일 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> exceedingElapsedTime() {
    ResponseDto result = new ResponseDto(ResponseCode.ERROR_3TIMES_OR_MORE, ResponseMessage.ERROR_3TIMES_OR_MORE);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
