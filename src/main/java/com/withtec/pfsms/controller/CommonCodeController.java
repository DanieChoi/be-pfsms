/*------------------------------------------------------------------------------
 * NAME : CommonCodeController.java
 * DESC : 공통코드 관련 요청이 진입하는 End Point
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/11  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withtec.pfsms.dto.response.system.commonCode.GetCommonCodeAllListResponseDto;
import com.withtec.pfsms.dto.response.system.commonCode.GetCommonCodeDetailListResponseDto;
import com.withtec.pfsms.service.CommonCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/commoncode")
@RequiredArgsConstructor
public class CommonCodeController {

  private final CommonCodeService commonCodeService;
  
 	/*
   * 공통코드 전체리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetCommonCodeAllListResponseDto>
	*/
  @GetMapping("/list")
  public ResponseEntity<? super GetCommonCodeAllListResponseDto> getCommonCodeList(
    @AuthenticationPrincipal UserDetails rsUserDetails
  ) {
    ResponseEntity<? super GetCommonCodeAllListResponseDto> response = commonCodeService.getCommonCodeList(rsUserDetails);
    return response;
  }

 	/*
   * 선택 공통코드에 대한 상세코드 리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @param String cmmnCd
	 * @return ResponseEntity<? super GetCommonCodeDetailListResponseDto>
	*/
  @GetMapping("/{cmmnCd}/detail")
  public ResponseEntity<? super GetCommonCodeDetailListResponseDto> getCommonCodeDetailList(
    @AuthenticationPrincipal UserDetails rsUserDetails,
    @PathVariable("cmmnCd") String cmmnCd
  ) {
    ResponseEntity<? super GetCommonCodeDetailListResponseDto> response = commonCodeService.getCommonCodeDetailList(rsUserDetails, cmmnCd);
    return response;
  }

}
