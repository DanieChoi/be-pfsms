/*------------------------------------------------------------------------------
 * NAME : CommonCodeService.java
 * DESC : 시스템 공통코드 관련 요청에 대한 서비스 인터페이스
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
package com.withtec.pfsms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.withtec.pfsms.dto.response.system.commonCode.GetCommonCodeAllListResponseDto;
import com.withtec.pfsms.dto.response.system.commonCode.GetCommonCodeDetailListResponseDto;

public interface CommonCodeService {
  
 	/*
   * 공통코드 전체리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetCommonCodeAllListResponseDto>
	*/
	ResponseEntity<? super GetCommonCodeAllListResponseDto> getCommonCodeList(UserDetails rsUserDetails);

 	/*
   * 선택 공통코드에 대한 상세코드 리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @param String cmmnCd
	 * @return ResponseEntity<? super GetCommonCodeDetailListResponseDto>
	*/
	ResponseEntity<? super GetCommonCodeDetailListResponseDto> getCommonCodeDetailList(UserDetails rsUserDetails, String cmmnCd);
}
