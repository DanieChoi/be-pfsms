/*------------------------------------------------------------------------------
 * NAME : PutSignInResponseDto.java
 * DESC : 로그아웃 API 호출 시 반환하는 DTO
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
package com.withtec.pfsms.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.UserEntity;

import lombok.Getter;

@Getter
public class PutSignOutResponseDto extends ResponseDto {
  
  private UserEntity rsUser;

 	/*
   * 생성자
	 * 
	*/
  private PutSignOutResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);    
  }

 	/*
   * 로그아웃 성공 시
	 * 
	 * @return ResponseEntity<PutSignOutResponseDto>
	*/
  public static ResponseEntity<PutSignOutResponseDto> success() {
    PutSignOutResponseDto result = new PutSignOutResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

}
