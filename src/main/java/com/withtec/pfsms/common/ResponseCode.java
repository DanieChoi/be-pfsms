/*------------------------------------------------------------------------------
 * NAME : ResponseCode.java
 * DESC : 반환 코드 정의
 * VER  : V1.0
 * PROJ : 상조관리시스템 구축 프로젝트
 * Copyright 2025 Withtec All rights reserved
 *------------------------------------------------------------------------------
 *                               MODIFICATION LOG
 *------------------------------------------------------------------------------
 *    DATE     AUTHOR                       DESCRIPTION
 * ----------  ------  -----------------------------------------------------------
 * 2025/07/14  최상원                       초기작성
 * 2025/08/11  최상원                       NOT_EXISTED_COMMON_DETAILCODE 추가
 *------------------------------------------------------------------------------*/
package com.withtec.pfsms.common;

public interface ResponseCode {
  
    // HTTP Status 200
    String SUCCESS = "SU";

    // HTTP Status 400(Bad Request)
    String NOT_EXISTED_AVAILABLE_TOPMENU = "BR001";          // 사용가능한 탑메뉴가 존재하지 않습니다.
    String NOT_EXISTED_MENU = "BR002";                       // 등록된 메뉴가 존재하지 않습니다.
    String NOT_EXISTED_COMMON_DETAILCODE = "BR002";          // 선택한 공통코드에 대한 상세코드가 존재하지 않습니다.

    // HTTP Status 401(Unauthorized)
    String NOT_EXISTED_LOGINID = "UN001";                    // 존재하지 않는 로그인 아이디
    String AUTHORIZATION_FAIL = "UN002";                     // 인증 실패

    // HTTP Status 403(Forbidden)
    String PASSWORD_ERROR = "FB001";                         // 비밀번호 오류
    String ERROR_3TIMES_OR_MORE = "FB002";                   // 비밀번호 3회이상 오류
    String EXCEEDING_ELAPSED_TIME = "FB003";                 // 비밀번호 변경 주기 일수 초과
    String NOT_EXISTED_ROLE = "FB004";                       // 역할이 설정되지 않았습니다.

    // HTTP Status 500(Internal Server Error)
    String DATABASE_ERROR = "ISE001";
    String SECURITY_CONTEXT_NOT_FOUND_ERROR = "ISE002";  //Security Context 에러입니다.
    String FILEUPLOAD_ERROR = "ISE003"; // 파일업로드 에러
}
