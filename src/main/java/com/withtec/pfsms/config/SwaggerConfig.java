/*------------------------------------------------------------------------------
 * NAME : SwaggerConfig.java
 * DESC : Swagger 구성설정
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
package com.withtec.pfsms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    // Security Scheme 정의
    SecurityScheme securityScheme = new SecurityScheme()
      .type(SecurityScheme.Type.HTTP)
      .scheme("bearer")
      .bearerFormat("JWT")
      .in(SecurityScheme.In.HEADER)
      .name("Authorization");

    // Security Requirement 정의
    SecurityRequirement securityRequirement = new SecurityRequirement().addList("BearerAuth");

    Info info = new Info()
      .title("상조업무관리시스템 API Document")
      .version("v0.0.1")
      .description("상조업무관리시스템의 API 명세서입니다.");
    
    return new OpenAPI()
      .info(info)
      .addSecurityItem(securityRequirement) // Security Requirement 추가
      .schemaRequirement("BearerAuth", securityScheme); // Security Scheme 추가
  }
  
}
