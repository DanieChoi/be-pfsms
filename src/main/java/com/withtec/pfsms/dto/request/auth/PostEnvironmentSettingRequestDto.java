/*------------------------------------------------------------------------------
 * NAME : PostEnvironmentSettingRequestDto.java
 * DESC : 로그인 사용자의 환경설정관련 서비스 요청할 때 필요한 정보값들을 모아놓은 Dto
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
package com.withtec.pfsms.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostEnvironmentSettingRequestDto {
  
  @NotBlank
  private String loginId;
}
