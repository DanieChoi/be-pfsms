/*------------------------------------------------------------------------------
 * NAME : JwtAuthenticationFilter.java
 * DESC : JWT 토큰으로 인증하고 SecurityContextHolder 에 추가하는 필터를 설정
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
package com.withtec.pfsms.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.withtec.pfsms.dto.object.CustomUserDetails;
import com.withtec.pfsms.entity.UserEntity;
import com.withtec.pfsms.provider.JwtProvider;
import com.withtec.pfsms.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    
    try {
      
      String token = parseBearerToken(request);

      if (jwtProvider.isExpired(token)) {
        filterChain.doFilter(request, response);
        return;
      }

      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }

      String email = jwtProvider.getEmail(token);
      if (email == null) {
        filterChain.doFilter(request, response);
        return;
      }

      String loginId = jwtProvider.getLoginId(token);
      if (loginId == null) {
        filterChain.doFilter(request, response);
        return;
      }

      UserEntity rsUser = userRepository.findByLoginId(loginId);

      CustomUserDetails customUserDetails = new CustomUserDetails(rsUser);

      AbstractAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
      
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authenticationToken);

      SecurityContextHolder.setContext(securityContext);

    } catch (Exception e) {
      e.printStackTrace();
    }

    filterChain.doFilter(request, response);
  }

  private String parseBearerToken(HttpServletRequest request) {

    String authorization = request.getHeader("Authorization");

    boolean hasAuthorization = StringUtils.hasText(authorization);
    if (!hasAuthorization) return null;

    boolean isBearer = authorization.startsWith("Bearer ");
    if (!isBearer) return null;

    String token = authorization.substring(7);

    return token;
  }
}
