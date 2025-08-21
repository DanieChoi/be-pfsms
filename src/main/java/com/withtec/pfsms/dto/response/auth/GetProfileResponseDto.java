/*------------------------------------------------------------------------------
 * NAME : GetProfileResponseDto.java
 * DESC : 로그인 사용자에 대한 프로필 정보 요청에 대한 API 호출 시 반환하는 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/18  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.UserEntity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GetProfileResponseDto extends ResponseDto {

  private String nationCd;
  private String nickname;
  private String email;
  private String proFileImageUrl;
  
	/*
   * 생성자
	 * 
   * @param UserEntity rsUser
 	 */
  private GetProfileResponseDto(
    UserEntity rsUser
  ) {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    this.nationCd = rsUser.getNationCd();
    this.nickname = rsUser.getNickname();
    this.email = rsUser.getEmail();
    this.proFileImageUrl = rsUser.getProfileImageUrl();
  }

  /* 
   * 로그인 사용자의 역할에 따라 사용가능한 메뉴리스트 가져오기 성공 시
   *  
   * @param UserEntity rsUser
   * @return ResponseEntity<GetProfileResponseDto>
   */
  public static ResponseEntity<GetProfileResponseDto> success(
    UserEntity rsUser
  ) {
    GetProfileResponseDto result = new GetProfileResponseDto(rsUser);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
