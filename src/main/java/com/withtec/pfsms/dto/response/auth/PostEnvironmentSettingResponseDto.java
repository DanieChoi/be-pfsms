/*------------------------------------------------------------------------------
 * NAME : GetEnvironmentSettingResponseDto.java
 * DESC : 로그인 사용자의 환경설정내역의 요청에 대한 API 호출 시 반환하는 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/30  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class PostEnvironmentSettingResponseDto extends ResponseDto {

  private Boolean useAlramPopup;        // 알람 팝업 사용여부
  private String workStartHours;    // 업무 시작시간
  private String workEndHours;      // 업무 종료시간
  private String dayOfWeekSetting;  // 업무 요일 설정값
  
	/*
   * 생성자
	 * 
   * @param 
 	 */
  private PostEnvironmentSettingResponseDto() {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    this.useAlramPopup = false;
    this.workStartHours = "0900";
    this.workEndHours = "1800";
    this.dayOfWeekSetting = "0111111";
  }

  /* 
   * 로그인 사용자의 환경설정 내역 가져오기 성공 시
   *  
   * @param 
   * @return ResponseEntity<GetEnvironmentSettingResponseDto>
   */
  public static ResponseEntity<PostEnvironmentSettingResponseDto> success(

  ) {
    PostEnvironmentSettingResponseDto result = new PostEnvironmentSettingResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
