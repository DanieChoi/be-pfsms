/*------------------------------------------------------------------------------
 * NAME : MenuRepository.java
 * DESC : 시스템 메뉴 엔티티에 의해 생성된 데이터베이스 테이블에 접근하는 메서드들을
 *        사용하기 위한 인터페이스
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/28  최상원                       초기작성
 * 2025/08/08  최상원                       findByMenuNo, findByUpperMenu 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.withtec.pfsms.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, String> {
  
  MenuEntity findByMenuNoAndIndictYn(String menuNo, String indictYn);
  MenuEntity findByMenuNo(String menuNo);
  List<MenuEntity> findByUpperMenuAndIndictYn(MenuEntity rsUpperMenu, String indictYn);
  List<MenuEntity> findByUpperMenu(MenuEntity rsUpperMenu);
}
