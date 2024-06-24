package com.bestswlkh0310.hertz.infra.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    ALREADY_EXISTS(HttpStatus.UNPROCESSABLE_ENTITY, "이미 존재합니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),

    /* 403: FORBIDDEN : 서버가 요청을 이해하지만 승인을 거부함  */
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근할 수 없습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */

    /* 500 INTERNAL SERVER ERROR : 서버가 브라우저나 클라이언트의 요청을 이행하지 못하게 하는 예기치 않은 조건이나 구성 문제가 발생했음 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다."),
}