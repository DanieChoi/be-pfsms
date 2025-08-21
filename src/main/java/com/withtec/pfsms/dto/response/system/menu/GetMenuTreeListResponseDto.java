/*------------------------------------------------------------------------------
 * NAME : GetMenuTreeListResponseDto.java
 * DESC : 전체 메뉴리스트 요청에 대한 API 호출 시 반환하는 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/08/05  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response.system.menu;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.object.MenuTreeListItem;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GetMenuTreeListResponseDto extends ResponseDto {
  
  private List<MenuTreeListItem> treeListMenus;

	/*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param String nationCd
   * @param List<MenuEntity> rsTreeMenuList
 	 */
  private GetMenuTreeListResponseDto(
    NameByLanguageRepository nameByLanguageRepository,
    String nationCd,
    List<MenuEntity> rsTreeMenuList
  ) {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    this.treeListMenus = rsTreeMenuList
      .stream()
      .map(rsSubMenu -> new MenuTreeListItem(nameByLanguageRepository, nationCd, rsSubMenu))
      .collect(Collectors.toList());
  }

  /* 
   * 전체 메뉴리스트 가져오기 성공 시
   *  
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param String nationCd
   * @param List<MenuEntity> rsTreeMenuList
   * @return ResponseEntity<GetMenuAllListResponseDto>
   */
  public static ResponseEntity<GetMenuTreeListResponseDto> success(
    NameByLanguageRepository nameByLanguageRepository,
    String nationCd,
    List<MenuEntity> rsTreeMenuList
  ) {
    GetMenuTreeListResponseDto result = new GetMenuTreeListResponseDto(nameByLanguageRepository, nationCd, rsTreeMenuList);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

 	/*
   * 전체 메뉴리스트 가져오기 실패 시(등록된 메뉴가 없을 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> notExistedMenu() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_MENU, ResponseMessage.NOT_EXISTED_MENU);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
