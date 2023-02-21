package com.sparta.board.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 400 Bad Request
    DUPLICATED_FOLDER_NAME(HttpStatus.BAD_REQUEST, "400_1", "중복폴더명이 이미 존재합니다."),

    // 404 Not Found
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "404_1", "해당 관심상품 아이디가 존재하지 않습니다."),
    NOT_FOUND_FOLDER(HttpStatus.NOT_FOUND, "404_2", "해당 폴더 아이디가 존재하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}