/*------------------------------------------------------------------------------
 * NAME : JwtProvider.java
 * DESC : Spring Security와 JWT를 사용하여 인증과 권한 부여를 처리
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
package com.withtec.pfsms.provider;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.withtec.pfsms.entity.UserEntity;

import io.jsonwebtoken.Jwts;

@Component
public class JwtProvider {
  
  private SecretKey secretKey;
  private int tokenExpirationTime;

  public JwtProvider(
    @Value("${jwt.tokenExpirationTime}")Integer tokenExpirationTime,
    @Value("${jwt.secret}")String secret
  ) {
    this.tokenExpirationTime = tokenExpirationTime;
    this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
  }

  public String getLoginId(String token) {
    String jwt = Jwts.parser()
      .verifyWith(secretKey).build()
      .parseSignedClaims(token)
      .getPayload()
      .get("loginId", String.class);
    return jwt;
  }

  public String getEmail(String token) {
    String jwt = Jwts.parser()
      .verifyWith(secretKey).build()
      .parseSignedClaims(token)
      .getPayload()
      .get("email", String.class);
    return jwt;
  }

  public String getUsername(String token) {
    String jwt = Jwts.parser()
      .verifyWith(secretKey).build()
      .parseSignedClaims(token)
      .getPayload()
      .get("username", String.class);
    return jwt;
  }

  public String getNickname(String token) {
    String jwt = Jwts.parser()
      .verifyWith(secretKey).build()
      .parseSignedClaims(token)
      .getPayload()
      .get("nickname", String.class);
    return jwt;
  }

  public String getProfileImage(String token) {
    String jwt = Jwts.parser()
      .verifyWith(secretKey).build()
      .parseSignedClaims(token)
      .getPayload()
      .get("profileImage", String.class);
    return jwt;
  }

  public Boolean isExpired(String token) {
  	if(!ObjectUtils.isEmpty(token)) {
      Boolean jwt = Jwts.parser()
        .verifyWith(secretKey).build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration().before(new Date());
      return jwt;    		
  	}else {
  		return true;
  	}
  }

  public String create(UserEntity rsUser) {
      
    String jwt = Jwts.builder()
      .claim("email", rsUser.getEmail())
      .claim("loginId", rsUser.getLoginId())
      .claim("nickname", rsUser.getNickname())
      .claim("profileImageUrl", rsUser.getProfileImageUrl())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 1000))
      .signWith(secretKey)
      .compact();
    return jwt;
  }
}
