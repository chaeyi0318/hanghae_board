package com.sparta.board.dto;

import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private List<CommentResponseDto> commentList;

//    private List<Board> boardList = new ArrayList<>();

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsers().getUsername();
        this.contents = board.getContents();
    }

    public BoardResponseDto(Board board, List<CommentResponseDto> commentList) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsers().getUsername();
        this.contents = board.getContents();
        this.commentList = commentList;
    }
}
