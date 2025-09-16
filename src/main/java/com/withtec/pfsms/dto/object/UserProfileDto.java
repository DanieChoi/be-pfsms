/*------------------------------------------------------------------------------
 * NAME : UserProfileDto.java
 * DESC : 사용자 프로필 항목 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/09/16  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.object;

import com.withtec.pfsms.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

  private String nationCd;
  private String nickname;
  private String email;
  private String proFileImageUrl;
  
	/**
   * 생성자
	 * 
   * @param UserEntity rsUser
 	 */
  private UserProfileDto(
    UserEntity rsUser
  ) {
    this.nationCd = rsUser.getNationCd();
    this.nickname = rsUser.getNickname();
    this.email = rsUser.getEmail();
    this.proFileImageUrl = rsUser.getProfileImageUrl();
  }

	/**
   * 사용자 프로필 정보 가져오기
	 * 
   * @param UserEntity rsUser
   * @return UserProfileDto rsProgramDtl
 	 */
  public static UserProfileDto setUserProfile(
    UserEntity rsUser
  ) {
    UserProfileDto dtoUserProfile = new UserProfileDto(rsUser);
    return dtoUserProfile;
  }
}
