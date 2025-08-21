/*------------------------------------------------------------------------------
 * NAME : UserRepository.java
 * DESC : 시스템 사용자 엔티티에 의해 생성된 데이터베이스 테이블에 접근하는 메서드들을
 *        사용하기 위한 인터페이스
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
package com.withtec.pfsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.withtec.pfsms.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
  
  UserEntity findByLoginId(String loginId);
}
