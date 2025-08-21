/*------------------------------------------------------------------------------
 * NAME : PatchChangePasswordResponseDto.java
 * DESC : 패스워드 변경 API 호출 시 반환하는 DTO
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

import lombok.Getter;

@Getter
public class PatchChangePasswordResponseDto extends ResponseDto {
  
	/*
   * 생성자
	 * 
	*/
  private PatchChangePasswordResponseDto() {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

 	/*
   * 패스워드 변경 성공 시
	 * 
	 * @return ResponseEntity<PatchUserPasswordResponseDto>
	*/
  public static ResponseEntity<PatchChangePasswordResponseDto> success() {
    PatchChangePasswordResponseDto result = new PatchChangePasswordResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
