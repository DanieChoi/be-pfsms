/*------------------------------------------------------------------------------
 * NAME : AuthServiceImpl.java
 * DESC : 인증관련 요청에 대한 서비스 구현체
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 * 2025/07/15  최상원                       로그아웃, 패스워드 찾기, 패스워드 변경 추가
 * 2025/08/18  최상원                       사용자 프로필 가져오기 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.withtec.pfsms.dto.request.auth.PatchChangePasswordRequestDto;
import com.withtec.pfsms.dto.request.auth.PostEnvironmentSettingRequestDto;
import com.withtec.pfsms.dto.request.auth.PostSignInRequestDto;
import com.withtec.pfsms.dto.response.ResponseDto;
import com.withtec.pfsms.dto.response.auth.PostEnvironmentSettingResponseDto;
import com.withtec.pfsms.dto.response.auth.GetProfileResponseDto;
import com.withtec.pfsms.dto.response.auth.PatchChangePasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PutFindPasswordResponseDto;
import com.withtec.pfsms.dto.response.auth.PostSignInResponseDto;
import com.withtec.pfsms.dto.response.auth.PutSignOutResponseDto;
import com.withtec.pfsms.dto.response.system.menu.GetListAvailableMenuResponseDto;
import com.withtec.pfsms.entity.MenuByRoleEntity;
import com.withtec.pfsms.entity.MenuEntity;
import com.withtec.pfsms.entity.RoleByUserEntity;
import com.withtec.pfsms.entity.UserEntity;
import com.withtec.pfsms.provider.JwtProvider;
import com.withtec.pfsms.repository.MenuByRoleRepository;
import com.withtec.pfsms.repository.MenuRepository;
import com.withtec.pfsms.repository.NameByLanguageRepository;
import com.withtec.pfsms.repository.RoleByUserRepository;
import com.withtec.pfsms.repository.UserRepository;
import com.withtec.pfsms.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;
  private final MenuRepository menuRepository;
  private final MenuByRoleRepository menuByRoleRepository;
  private final RoleByUserRepository roleByUserRepository;
  private final NameByLanguageRepository nameByLanguageRepository;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

 	/*
   * 로그인
	 * 
	 * @param PutSignInRequestDto requestBody
	 * @param HttpServletRequest  request
	 * @return ResponseEntity<? super PutSignInResponseDto>
	*/
  @Override
  public ResponseEntity<? super PostSignInResponseDto> signIn(
    PostSignInRequestDto requestBody,
    HttpServletRequest request
  ) {
    String token;
    UserEntity rsUser;
    List<MenuEntity> rsListTopMenu = new ArrayList<>();
    List<MenuByRoleEntity> rsListAvailableMenu = new ArrayList<>();

    try {

      String strLoginId = requestBody.getLoginId();
      rsUser = userRepository.findByLoginId(strLoginId);
      if (rsUser == null) return PostSignInResponseDto.notExistLoginId();

      boolean isFirstLogin = false;

      if(rsUser.getFrstLoginDttm() == null) isFirstLogin = true;

      //비밀번호 변경 주기 일수 초과 여부 확인
      if(!isFirstLogin) {
        Duration elapsedTime = Duration.between(Instant.now(), rsUser.getPasswordChangeDttm());

        Long elapsedDay = elapsedTime.getSeconds()/86400;

        if(elapsedDay >= rsUser.getPasswordChangeCycleDayCount()) return PostSignInResponseDto.exceedingElapsedTime();

      }

      String strPassword = requestBody.getPassword();
      String encodedPassword = rsUser.getPassword();
      boolean isMatched = passwordEncoder.matches(strPassword, encodedPassword);

      //패스워드를 잘못 입력했을 경우
      if(!isMatched) {
        //해당 사용자의 패스워드 실패 횟수를 증가시킨다.
        rsUser.pwdFailUpdate();
        userRepository.save(rsUser);

        //비밀번호 오류횟수가 3회이상인 경우
        if(rsUser.getPwdErrNotm() >= 3) {
          return PostSignInResponseDto.passwordError3TimesOrMore();
        }
        return PostSignInResponseDto.passwordError();
      }

      //JWT토큰 생성
      token = jwtProvider.create(rsUser);

      rsUser.loginChkTime(isFirstLogin);
      rsUser.pwdFailCountBack();          //정상적으로 로그인을 했을 경우 로그인 실패 횟수를 초기화함.
      userRepository.save(rsUser);

      MenuEntity rsUpperMenu = menuRepository.findByMenuNoAndIndictYn("MNU000", "Y");
      rsListTopMenu = menuRepository.findByUpperMenuAndIndictYn(rsUpperMenu, "Y");
      if(rsListTopMenu == null) return GetListAvailableMenuResponseDto.availableTopMenu();

      // 사용자에게 할당된 역할
      List<RoleByUserEntity> rsListRoleByUser = roleByUserRepository.findByUserAndUseYn(rsUser, "Y");
      if(rsListRoleByUser == null) return ResponseDto.notExistRole();

      for(RoleByUserEntity rsRoleByUser : rsListRoleByUser) {
        List<MenuByRoleEntity> rsListMenuByRole = menuByRoleRepository.findByRoleAndUseYn(rsRoleByUser.getRole(), "Y");

        // rsListAvailableMenu에서 rsListMenuByRole 내역을 제거합니다.
        rsListAvailableMenu.removeAll(rsListMenuByRole);

        // rsListAvailableMenu에서 rsListMenuByRole 내역을 추가합니다.
        rsListAvailableMenu.addAll(rsListMenuByRole);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return PostSignInResponseDto.success(nameByLanguageRepository, token, rsUser, rsListTopMenu, rsListAvailableMenu);
  }

 	/*
   * 사용자 프로필 가져오기
	 * 
	 * @param UserDetails rsUserDetails
	 * @return ResponseEntity<? super GetProfileResponseDto>
	*/
  @Override
  public ResponseEntity<? super GetProfileResponseDto> getProfile(UserDetails rsUserDetails) {

    UserEntity rsUser = null;
    try {
      
      if(rsUserDetails == null) return ResponseDto.noAuthization();

      rsUser = userRepository.findByLoginId(rsUserDetails.getUsername());
      if(rsUser == null) return ResponseDto.notExistLoginId();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }
    return GetProfileResponseDto.success(rsUser);
  }
  

 	/*
   * 로그아웃
	 * 
	 * @param UserDetails ud
	 * @param HttpServletRequest  hr
	 * @return ResponseEntity<? super PutSignOutResponseDto>
	*/
  @Override
  public ResponseEntity<? super PutSignOutResponseDto> signOut(
    UserDetails ud,
    HttpServletRequest hr
  ) {

    try {
      UserEntity rsUser = userRepository.findByLoginId(ud.getUsername());

      //로그아웃 시간 기록하기
      rsUser.logOutChkTime();
      userRepository.save(rsUser);

      //세션 삭제하기
      HttpSession session = hr.getSession(false);
      if(session != null) session.invalidate();
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return PutSignOutResponseDto.success();
  }

 	/*
   * 패스워드 찾기
	 * 
	 * @param String loginId
	 * @return ResponseEntity<? super PutFindPasswordResponseDto>
	*/
  @Override
  public ResponseEntity<? super PutFindPasswordResponseDto> findPassword(
    String loginId
  ) {

    try {

      UserEntity rsUser = userRepository.findByLoginId(loginId);
      if(rsUser == null) return ResponseDto.notExistLoginId();

      //찾은 비밀번호를 문자로 통지할 것인지 비밀번호 재설정 페이지 링크가 포함된  메일을 발송할 것인지에 따라 코딩필요.
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return PutFindPasswordResponseDto.success();
  }

 	/*
   * 패스워드 변경
	 * 
	 * @param PatchChangePasswordRequestDto requestBody
	 * @return ResponseEntity<? super PatchChangePasswordResponseDto>
	*/
  @Override
  public ResponseEntity<? super PatchChangePasswordResponseDto> changePassword(
    PatchChangePasswordRequestDto requestBody
  ) {

    try {
      
      UserEntity rsUser = userRepository.findByLoginId(requestBody.getLoginId());
      if(rsUser == null) return ResponseDto.notExistLoginId();

      String encodedNewPassword = passwordEncoder.encode(requestBody.getChangePassword());

      log.info(">>>기존 패스워드: {}, 변경 패스워드: {}", rsUser.getPassword(), requestBody.getChangePassword());

      rsUser.ChangePassword(encodedNewPassword);
      userRepository.save(rsUser);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return PatchChangePasswordResponseDto.success();
  }

  /*
   *  사용자별 환경설정 가져오기
   *
   *  @param PostEnvironmentSettingRequestDto requestBody 전달 개체 DTO
   *  @return ResponseEntity<? super GetEnvironmentSettingResponseDto>
   */
  @Override
  public ResponseEntity<? super PostEnvironmentSettingResponseDto> getEnvironmentSetting(PostEnvironmentSettingRequestDto requestBody) {
    try {
      
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDto.datebaseError();
    }

    return PostEnvironmentSettingResponseDto.success();
  }

}
