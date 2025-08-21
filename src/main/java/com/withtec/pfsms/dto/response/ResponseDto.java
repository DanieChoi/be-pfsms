/*------------------------------------------------------------------------------
 * NAME : ResponseDto.java
 * DESC : API 호출 시 반환하는 기본적인 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 * 2025/07/15  최상원                       존재하지 않는 로그인 아이디로 인증을 시도했을 때 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
  
  private String rtnCode;
  private String rtnMessage;

    /*
     * 데이타 베이스 관련 오츄가 발생했을 때
     * 
     * @return ResponseEntity<ResponseDto>
    */
    public static ResponseEntity<ResponseDto> datebaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    /*
     * 보안관련 오류 발생 시
     * 
     * @return ResponseEntity<ResponseDto>
    */
    public static ResponseEntity<ResponseDto> securityContextNotFoundException() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SECURITY_CONTEXT_NOT_FOUND_ERROR, ResponseMessage.SECURITY_CONTEXT_NOT_FOUND_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

 	/*
     * 파일 업로드 시 발생하는 오류 시
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
    public static ResponseEntity<ResponseDto> fileUploadError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.FILEUPLOAD_ERROR, ResponseMessage.FILEUPLOAD_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

 	/*
     * 존재하지 않는 로그인 아이디로 인증을 시도했을 때
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
    public static ResponseEntity<ResponseDto> notExistLoginId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_LOGINID, ResponseMessage.NOT_EXISTED_LOGINID);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

	/*
     * 로그인 정보가 없거나 일치하지 않을 경우
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
    public static ResponseEntity<ResponseDto> noAuthization() {
        ResponseDto result = new ResponseDto(ResponseCode.AUTHORIZATION_FAIL, ResponseMessage.AUTHORIZATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

	/*
     * 역할이 설정되지 않은 사용자일 경우
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
    public static ResponseEntity<ResponseDto> notExistRole() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_ROLE, ResponseMessage.NOT_EXISTED_ROLE);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
