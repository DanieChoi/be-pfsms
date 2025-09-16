/*------------------------------------------------------------------------------
 * NAME : PostSignInResponseDto.java
 * DESC : 인증관련 API 호출 시 반환하는 DTO
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 * 2025/09/16  최상원                       사용자프로필과 사용가능 메뉴리스트 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.dto.response.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.withtec.pfsms.common.ResponseCode;
import com.withtec.pfsms.common.ResponseMessage;
import com.withtec.pfsms.dto.object.AvailableMenuListItem;
import com.withtec.pfsms.dto.object.UserProfileDto;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.entity.MenuByRoleEntity;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.entity.UserEntity;
import com.withtec.pfsms.repository.NameByLanguageRepository;

import lombok.Getter;

@Getter
public class PostSignInResponseDto extends ResponseDto {
  
  private String tokenKey;
  private Long expiresIn;
  private UserProfileDto userProfile;
  private List<AvailableMenuListItem> availableMenuList;

 	/**
   * 생성자
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
	 * @param String  token
	 * @param UserEntity rsUser
   * @param List<MenuEntity> rsListTopMenu
   * @param List<MenuByRoleEntity> rsListAvailableMenuByRole
 	 */
  private PostSignInResponseDto(
    NameByLanguageRepository nameByLanguageRepository,
    String token,
    UserEntity rsUser,
    List<MenuEntity> rsListTopMenu,
    List<MenuByRoleEntity> rsListAvailableMenuByRole
  ) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.tokenKey = token;
    this.expiresIn = 3600L;
    this.userProfile = UserProfileDto.setUserProfile(rsUser);

    List<MenuEntity> rsListAvailableMenus = new ArrayList<>();
    for(MenuEntity rsMenu : rsListTopMenu) {
      for(MenuByRoleEntity rsMenuByRole : rsListAvailableMenuByRole) {
        if(rsMenu.getMenuNo().equals(rsMenuByRole.getMenu().getMenuNo())) rsListAvailableMenus.add(rsMenu);
      }
    }

    this.availableMenuList = rsListAvailableMenus
      .stream()
      .map(rsAvailableMenus -> new AvailableMenuListItem(nameByLanguageRepository, rsUser.getNationCd(), rsAvailableMenus, rsListAvailableMenuByRole))
      .collect(Collectors.toList());
  }

 	/**
   * 로그인 성공 시
	 * 
   * @param NameByLanguageRepository nameByLanguageRepository
	 * @param String  token
	 * @param UserEntity rsUser
   * @param List<MenuEntity> rsListTopMenu
   * @param List<MenuByRoleEntity> rsListAvailableMenuByRole
	 * @return ResponseEntity<PutSignInResponseDto>
	 */
  public static ResponseEntity<PostSignInResponseDto> success(
    NameByLanguageRepository nameByLanguageRepository,
    String token,
    UserEntity rsUser,
    List<MenuEntity> rsListTopMenu,
    List<MenuByRoleEntity> rsListAvailableMenuByRole
  ) {
    PostSignInResponseDto result = new PostSignInResponseDto(nameByLanguageRepository, token, rsUser, rsListTopMenu, rsListAvailableMenuByRole);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

 	/**
   * 로그인 실패 시(로그인 ID가 존재하지 않을 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	 */
  public static ResponseEntity<ResponseDto> notExistLoginId() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_LOGINID, ResponseMessage.NOT_EXISTED_LOGINID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

 	/**
   * 로그인 실패 시(패스워드 입력 오류인 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	 */
  public static ResponseEntity<ResponseDto> passwordError() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_LOGINID, ResponseMessage.NOT_EXISTED_LOGINID);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

 	/**
   * 로그인 실패 시(패스워드 입력 오류가 3회이상인 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	 */
  public static ResponseEntity<ResponseDto> passwordError3TimesOrMore() {
    ResponseDto result = new ResponseDto(ResponseCode.ERROR_3TIMES_OR_MORE, ResponseMessage.ERROR_3TIMES_OR_MORE);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

 	/**
   * 로그인 실패 시(패스워드 변경 주기 일자 초과일 경우)
	 * 
	 * @return ResponseEntity<ResponseDto>
	 */
  public static ResponseEntity<ResponseDto> exceedingElapsedTime() {
    ResponseDto result = new ResponseDto(ResponseCode.ERROR_3TIMES_OR_MORE, ResponseMessage.ERROR_3TIMES_OR_MORE);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}
