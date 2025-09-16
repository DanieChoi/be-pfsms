/*------------------------------------------------------------------------------
 * NAME : CustomUserDetails.java
 * DESC : 스프링 시큐리티가 사용자의 인증 및 권한 부여를 위해 필요한 정보를 제공
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
package com.withtec.pfsms.dto.object;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.withtec.pfsms.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
  
  private final UserEntity userEntity;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return userEntity.getLoginId();
      }
    });
    return collection;
  }

  @Override
  public String getUsername() {
    return userEntity.getLoginId();
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }
}
