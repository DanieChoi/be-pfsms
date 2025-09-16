/*------------------------------------------------------------------------------
 * NAME : CommonCodeServiceImpl.java
 * DESC : 시스템 공통코드 관련 요청에 대한 서비스 구현체
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/28  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.dto.response.system.commonCode.GetCommonCodeAllListResponseDto;
import com.withtec.pfsms.dto.response.system.commonCode.GetCommonCodeDetailListResponseDto;
import com.withtec.pfsms.entity.CommonCodeEntity;
import com.withtec.pfsms.entity.CommonDetailCodeEntity;
import com.withtec.pfsms.entity.UserEntity;
import com.withtec.pfsms.repository.CommonCodeRepository;
import com.withtec.pfsms.repository.CommonDetailCodeRepository;
import com.withtec.pfsms.repository.NameByLanguageRepository;
import com.withtec.pfsms.repository.UserRepository;
import com.withtec.pfsms.service.CommonCodeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonCodeServiceImpl implements CommonCodeService {

  private final UserRepository userRepository;
  private final NameByLanguageRepository nameByLanguageRepository;
  private final CommonCodeRepository commonCodeRepository;
  private final CommonDetailCodeRepository commonDetailCodeRepository;

 	/*
   * 공통코드 전체리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetCommonCodeAllListResponseDto>
	*/
  @Override
  public ResponseEntity<? super GetCommonCodeAllListResponseDto> getCommonCodeList(
    UserDetails rsUserDetails
  ) {

    List<CommonCodeEntity> rsCommonCodeList = new ArrayList<>();

    try {
      
      if(rsUserDetails == null) return ResponseDto.noAuthization();

      UserEntity rsUser = userRepository.findByLoginId(rsUserDetails.getUsername());
      if(rsUser == null) return ResponseDto.notExistLoginId();

      rsCommonCodeList = commonCodeRepository.findAll();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return GetCommonCodeAllListResponseDto.success(nameByLanguageRepository, rsCommonCodeList);
  }

 	/*
   * 선택 공통코드에 대한 상세코드 리스트
	 * 
	 * @param UserDetails rsUserDetails
	 * @param String cmmnCd
	 * @return ResponseEntity<? super GetCommonCodeDetailListResponseDto>
	*/
  @Override
  public ResponseEntity<? super GetCommonCodeDetailListResponseDto> getCommonCodeDetailList(
    UserDetails rsUserDetails,
    String cmmnCd
  ) {

    List<CommonDetailCodeEntity> rsCommonDetailCodeList = new ArrayList<>();

    try {
      
      if(rsUserDetails == null) return ResponseDto.noAuthization();

      UserEntity rsUser = userRepository.findByLoginId(rsUserDetails.getUsername());
      if(rsUser == null) return ResponseDto.notExistLoginId();

      rsCommonDetailCodeList = commonDetailCodeRepository.findByCmmnCd(cmmnCd);
      if(rsCommonDetailCodeList == null) return GetCommonCodeDetailListResponseDto.notExistCommonDetailCode();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return GetCommonCodeDetailListResponseDto.success(nameByLanguageRepository, rsCommonDetailCodeList);
  }
  
}
