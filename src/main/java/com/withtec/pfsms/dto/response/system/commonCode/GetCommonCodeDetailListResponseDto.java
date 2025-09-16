/*------------------------------------------------------------------------------
 * NAME : GetCommonCodeDetailListResponseDto.java
 * DESC : 선택 공통코드에 대한 상세 리스트 요청에 대한 API 호출 시 반환하는 DTO
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
import com.withtec.pfsms.dto.object.CommonDetailCodeListItem;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.CommonDetailCodeEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GetCommonCodeDetailListResponseDto extends ResponseDto {
  
  List<CommonDetailCodeListItem> commonDetailCodeList;

	/*
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param List<CommonDetailCodeEntity> rsCommonDetailCodeList
 	 */
  private GetCommonCodeDetailListResponseDto(
    NameByLanguageRepository nameByLanguageRepository,
    List<CommonDetailCodeEntity> rsCommonDetailCodeList
  ) {

    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.commonDetailCodeList = CommonDetailCodeListItem.getCommonDetailCodeList(nameByLanguageRepository, rsCommonDetailCodeList);
  }

  /* 
   * 선택 공통코드에 대한 상세코드 리스트 가져오기 성공 시
   *  
   * @param NameByLanguageRepository nameByLanguageRepository
   * @param List<CommonDetailCodeEntity> rsCommonDetailCodeList
   * @return ResponseEntity<GetCommonCodeDetailListResponseDto>
   */
  public static ResponseEntity<GetCommonCodeDetailListResponseDto> success(
    NameByLanguageRepository nameByLanguageRepository,
    List<CommonDetailCodeEntity> rsCommonDetailCodeList
  ) {

    GetCommonCodeDetailListResponseDto result = new GetCommonCodeDetailListResponseDto(nameByLanguageRepository, rsCommonDetailCodeList);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

 	/*
   * 선택 공통코드에 대한 상세코드 리스트 가져오기 실패 시(상세코드가 없는 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	*/
  public static ResponseEntity<ResponseDto> notExistCommonDetailCode() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_COMMON_DETAILCODE, ResponseMessage.NOT_EXISTED_COMMON_DETAILCODE);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
