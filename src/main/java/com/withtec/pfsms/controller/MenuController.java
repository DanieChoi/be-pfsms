/*------------------------------------------------------------------------------
 * NAME : MenuController.java
 * DESC : 메뉴관련 요청이 진입하는 End Point
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/28  최상원                       초기작성
 * 2025/08/06  최상원                       메뉴 전체 리스트
 * 2025/08/10  최상원                       선택 메뉴 상세내역
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withtec.pfsms.dto.response.system.menu.GetDetailMenuResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetListAvailableMenuResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetMenuTreeListResponseDto;
import com.withtec.pfsms.service.MenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Tag(name = "Menu-Controller", description = "메뉴관련 API 엔드포인트")
@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

  private final MenuService menuService;

 	/*
   * 메뉴 트리 리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetMenuAllListResponseDto>
	*/
  @GetMapping("/tree-list")
  public ResponseEntity<? super GetMenuTreeListResponseDto> getMenuTreeList(
    @AuthenticationPrincipal UserDetails rsUserDetails
  ) {
    ResponseEntity<? super GetMenuTreeListResponseDto> response = menuService.getMenuTreeList(rsUserDetails);
    return response;
  }

 	/*
   * 선택 메뉴 상세내역
	 * 
	 * @param String menuNo
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetDetailMenuResponseDto>
	*/
  @GetMapping("/{menuNo}/detail")
  public ResponseEntity<? super GetDetailMenuResponseDto> getDetailOfMenu(
    @PathVariable("menuNo") String menuNo,
    @AuthenticationPrincipal UserDetails rsUserDetails
  ) {
    ResponseEntity<? super GetDetailMenuResponseDto> response = menuService.getDetailOfMenu(menuNo, rsUserDetails);
    return response;
  }

 	/*
   * 역활 별 사용가능 메뉴리스트
	 * 
	 * @param String upperMenuNo
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetListAvailableMenuResponseDto>
	*/
  @GetMapping("/{upperMenuNo}/available-menus")
  @Operation(
    summary = "역활 별 사용가능 메뉴리스트",
    description = "로그인 사용자의 역할에 따른 사용가능한 메뉴리스트 가져오기.",
    responses = {
      @ApiResponse(responseCode = "200", description = "사용가능 메뉴리스트 가져오기 성공")
    },
    parameters = {
      @Parameter(name = "upperMenuNo", description = "가져오려는 사용가능 메뉴의 최상위 메뉴코드", required = true, example = "MNU000")
    }
  )  
  public ResponseEntity<? super GetListAvailableMenuResponseDto> getListOfAvailableMenusByRole(
    @PathVariable("upperMenuNo") String upperMenuNo,
    @AuthenticationPrincipal UserDetails rsUserDetails
  ) {
    ResponseEntity<? super GetListAvailableMenuResponseDto> response = menuService.getListOfAvailableMenusByRole(upperMenuNo, rsUserDetails);
    return response;
  }  
}
