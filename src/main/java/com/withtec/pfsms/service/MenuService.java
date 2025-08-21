/*------------------------------------------------------------------------------
 * NAME : MenuService.java
 * DESC : 시스템 메뉴관련 요청에 대한 서비스 인터페이스
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
package com.withtec.pfsms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.withtec.pfsms.dto.response.system.menu.GetDetailMenuResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetListAvailableMenuResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetMenuTreeListResponseDto;

public interface MenuService {
  
 	/*
   * 메뉴 트리 리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetMenuAllListResponseDto>
	*/
	ResponseEntity<? super GetMenuTreeListResponseDto> getMenuTreeList(UserDetails rsUserDetails);

 	/*
   * 선택 메뉴 상세내역
	 * 
	 * @param String menuNo
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetDetailMenuResponseDto>
	*/
	ResponseEntity<? super GetDetailMenuResponseDto> getDetailOfMenu(String menuNo, UserDetails rsUserDetails);

 	/*
   * 역활 별 사용가능 메뉴리스트
	 * 
	 * @param String upperMenuNo
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetListAvailableMenuResponseDto>
	*/
	ResponseEntity<? super GetListAvailableMenuResponseDto> getListOfAvailableMenusByRole(String upperMenuNo, UserDetails rsUserDetails);
}