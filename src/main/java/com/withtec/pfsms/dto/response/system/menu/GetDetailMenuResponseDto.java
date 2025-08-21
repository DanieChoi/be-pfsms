/*------------------------------------------------------------------------------
 * NAME : GetDetailMenuResponseDto.java
 * DESC : 선택 메뉴의 상세내역 요청에 대한 API 호출 시 반환하는 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/08  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response.system.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.object.ProgramDetailItem;
import com.withtec.pfsms.dto.object.NameByLanguageListItem;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.entity.NameByLanguageEntity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GetDetailMenuResponseDto extends ResponseDto {

  private String menuNo;                      // 메뉴번호
  private List<NameByLanguageListItem> menuNm;  // 언어별 메뉴명리스트
  private ProgramDetailItem progrm;           // 프로그램
  private String menuIconUrl;                 // 메뉴 아이콘 경로
  private String menuHref;                    // 접근경로
  private String menuDcCntnt;                 // 메뉴 설명내용
  private String menuLevelCd;                 // 메뉴 수준코드
  private String menuSeCd;                    // 메뉴 구분코드
  private int menuOrdr;                       // 메뉴 표시순서
  private String menuLclasCd;                 // 메뉴 대분류코드
  private String menuMclasCd;                 // 메뉴 중분류코드
  private String menuSclasCd;                 // 메뉴 소분류코드
  private String menuDtlClfcCd;               // 메뉴 상세분류코드
  private String indictYn;                    // 메뉴 표시여부


	/*
   * 생성자
	 * 
   * @param MenuEntity rsMenu
   * @param List<NameByLanguageEntity> rsNameByLangList
 	 */
  private GetDetailMenuResponseDto(
    MenuEntity rsMenu,
    List<NameByLanguageEntity> rsNameByLangList
  ) {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.menuNo = rsMenu.getMenuNo();
    this.menuNm = NameByLanguageListItem.getMenuNameByLang(rsNameByLangList);
    this.progrm = ProgramDetailItem.getProgrmDtl(rsMenu.getProgram());
    this.menuIconUrl = rsMenu.getMenuIconUrl();
    this.menuHref = rsMenu.getMenuHref();
    this.menuDcCntnt = rsMenu.getMenuDcCntnt();
    this.menuLevelCd = rsMenu.getMenuLevelCd();
    this.menuSeCd = rsMenu.getMenuSeCd();
    this.menuOrdr = rsMenu.getMenuOrdr();
    this.menuLclasCd = rsMenu.getMenuLclasCd();
    this.menuMclasCd = rsMenu.getMenuMclasCd();
    this.menuSclasCd = rsMenu.getMenuSclasCd();
    this.menuDtlClfcCd = rsMenu.getMenuDtlClfcCd();
    this.indictYn = rsMenu.getIndictYn();
  }

  /* 
   * 선택 메뉴의 상세내역 가져오기 성공 시
   *  
   * @param MenuEntity rsMenu
   * @param List<NameByLanguageEntity> rsNameByLangList
   * @return ResponseEntity<GetDetailMenuResponseDto>
   */
  public static ResponseEntity<GetDetailMenuResponseDto> success(
    MenuEntity rsMenu,
    List<NameByLanguageEntity> rsNameByLangList
  ) {

    GetDetailMenuResponseDto result = new GetDetailMenuResponseDto(rsMenu, rsNameByLangList);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}