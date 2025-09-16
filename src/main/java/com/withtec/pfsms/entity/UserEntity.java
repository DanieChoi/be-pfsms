/*------------------------------------------------------------------------------
 * NAME : UserEntity.java
 * DESC : 시스템 사용자 엔티티
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.entity;

import java.time.Instant;

import com.withtec.pfsms.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tsy_user")
@Table(name = "tsy_user")
public class UserEntity extends BaseTimeEntity {
  
  @Id
  @Column(name = "LOGIN_ID", nullable = false)
  private String loginId;                               // 로그인 아이디

  @Column(name = "PASSWORD", nullable = false)
  private String password;                              // 패스워드

  @Column(name = "PRE_PASSWORD", nullable = false)
  private String prePassword;                           // 이전패스워드

  @Column(name = "NATION_CD", nullable = true)
  private String nationCd;                              // 국가코드

  @Column(name = "USR_KIND_CD", nullable = true)
  private String usrKindCd;                             // 사용자구분코드

  @Column(name = "NICKNAME", nullable = false)
  private String nickname;                              // 별명

  @Column(name = "EMAIL_ADRES", nullable = false)
  private String email;                                 // 이메일주소

  @Column(name = "PWD_ERR_NOTM", nullable = false)
  private int pwdErrNotm;                               // 패스워드오류횟수

  @Column(name = "FRST_LOGIN_DTTM", nullable = true)
  private Instant frstLoginDttm;                        // 최초로그인일시

  @Column(name = "LAST_LOGIN_DTTM", nullable = true)
  private Instant lastLoginDttm;                        // 최종로그인일시

  @Column(name = "LAST_LOGOUT_DTTM", nullable = true)
  private Instant lastLogoutDttm;                       // 최종로그아웃일시

  @Column(name = "PWD_CHG_DTTM", nullable = true)
  private Instant passwordChangeDttm;                   // 패스워드변경일시

  @Column(name = "PWD_CHG_CYCL_DAYCNT", nullable = false)
  private Long passwordChangeCycleDayCount;             // 패스워드변경주기일수

  @Column(name = "PROFILE_IMAGE_URL", nullable = true)
  private String profileImageUrl;                       // 프로필사진경로

  @Column(name = "USE_YN", nullable = true)
  private String useYn;                                 // 사용여부

  @Column(name = "REGTR_ID", nullable = true)
  private String regtrId;                               // 등록자ID

  @Column(name = "UPDUSR_ID", nullable = true)
  private String updusrId;                              // 수정자ID

	/*
   * 패스워드 변경 처리
	 * 
	*/
  public void ChangePassword(String changePassword){
    this.prePassword = this.password;
    this.password = changePassword;
    this.passwordChangeDttm = Instant.now();
  }

	/*
   * 로그인할때 비밀번호 실패시 카운트 증가
	 * 
	*/
  public void pwdFailUpdate(){
    this.pwdErrNotm++;
  }

	/*
   * 로그인 성공했을때 비밀번호실패 카운트를 0으로 초기화
	 * 
	*/
  public void pwdFailCountBack(){
    this.pwdErrNotm = 0;
  }

	/*
   * 로그인 시간 체크로직
	 * 
	*/
  public void loginChkTime(boolean isFirst){
    if(isFirst) {
      this.frstLoginDttm = Instant.now();
      this.lastLoginDttm = this.frstLoginDttm;
      this.passwordChangeDttm = Instant.now();
    } else {
      this.lastLoginDttm = Instant.now();
    }
  }

	/*
   * 로그아웃 시간 체크로직
	 * 
	*/
  public void logOutChkTime(){
    this.lastLogoutDttm = Instant.now();
  }
}
