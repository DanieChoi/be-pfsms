/*------------------------------------------------------------------------------
 * NAME : GetCommonCodeAllListResponseDto.java
 * DESC : 공통코드의 전체 리스트 요청에 대한 API 호출 시 반환하는 DTO
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
package com.withtec.pfsms.dto.response.system.commonCode;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.object.CommonCodeListItem;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.CommonCodeEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GetCommonCodeAllListResponseDto extends ResponseDto {

  List<CommonCodeListItem> commonCodeList;
  
	/*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param List<CommonCodeEntity> rsCommonCodeList
 	 */
  private GetCommonCodeAllListResponseDto(
    NameByLanguageRepository nameByLanguageRepository,
    List<CommonCodeEntity> rsCommonCodeList
  ) {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.commonCodeList = CommonCodeListItem.getCommonCodeList(nameByLanguageRepository, rsCommonCodeList);
  }

  /* 
   * 공통코드 전체 리스트 가져오기 성공 시
   *  
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param List<CommonCodeEntity> rsCommonCodeList
   * @return ResponseEntity<GetCommonCodeAllListResponseDto>
   */
  public static ResponseEntity<GetCommonCodeAllListResponseDto> success(
    NameByLanguageRepository nameByLanguageRepository,
    List<CommonCodeEntity> rsCommonCodeList
  ) {

    GetCommonCodeAllListResponseDto result = new GetCommonCodeAllListResponseDto(nameByLanguageRepository, rsCommonCodeList);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
