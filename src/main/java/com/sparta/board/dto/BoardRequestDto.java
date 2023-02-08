package com.sparta.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String writer;
    private String pw;
    private String contents;
}
