/*------------------------------------------------------------------------------
 * NAME : ResponseMessage.java
 * DESC : 반환 메시지 정의
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

public interface ResponseMessage {
  
    // HTTP Status 200
    String SUCCESS = "Success.";

    // HTTP Status 400(Bad Request)
    String NOT_EXISTED_AVAILABLE_TOPMENU = "사용가능한 탑메뉴가 존재하지 않습니다.";
    String NOT_EXISTED_MENU = "등록된 메뉴가 존재하지 않습니다.";
    String NOT_EXISTED_COMMON_DETAILCODE = "선택한 공통코드에 대한 상세코드가 존재하지 않습니다.";

    // HTTP Status 401(Unauthorized)
    String NOT_EXISTED_LOGINID = "존재하지 않는 로그인 아이디입니다.";
    String AUTHORIZATION_FAIL = "인증에 실패했습니다.";

    // HTTP Status 403(Forbidden)
    String PASSWORD_ERROR = "비밀번호를 잘못 입력하셨습니다.";
    String ERROR_3TIMES_OR_MORE = "비밀번호 3회이상 잘못 입력하셨습니다.";
    String EXCEEDING_ELAPSED_TIME = "비밀번호 변경 주기 일수를 초과하였습니다.";
    String NOT_EXISTED_ROLE = "역할이 설정되지 않았습니다.";

    // HTTP Status 500(Internal Server Error)
    String DATABASE_ERROR = "Database error.";
    String SECURITY_CONTEXT_NOT_FOUND_ERROR = "Security context error.";
    String FILEUPLOAD_ERROR = "FileUpload error.";
}
