package com.unithon.team15_server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //400 Bad Request
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "400-1", "입력값이 올바르지 않습니다."),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "400-2", "요청 값이 누락되었습니다."),

    //401 Unauthorized
    ERROR_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401-1", "인증 정보가 없습니다."),
    INVALID_TOKEN_FORMAT(HttpStatus.UNAUTHORIZED, "401-2", "토큰의 형식이 유효하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "401-3", "토큰 기간이 만료됐습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "401-5", "비밀번호가 일치하지 않습니다."),
    INVALID_CODE(HttpStatus.UNAUTHORIZED, "401-6", "인증코드가 유효하지 않습니다."),
    EXPIRED_CODE(HttpStatus.UNAUTHORIZED, "401-7", "인증코드가 만료되었습니다."),

    //403 Forbidden

    //404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404-1", "해당 회원이 존재하지 않습니다."),
    EMPLOY_CHECK_NOT_FOUND(HttpStatus.NOT_FOUND, "404-2", "해당 Employ Check가 존재하지 않습니다."),

    //409 Conflict
    USER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "409-1", "이미 존재하는 회원입니다.."),
    NICKNAME_ALREADY_REGISTERED(HttpStatus.CONFLICT, "409-2", "이미 등록된 닉네임입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
