package com.sparta.board.dto;

import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private LocalDateTime createdAt;
    private String comments;
    private String username;


    public CommentResponseDto(Comment comment) {
        this.createdAt = comment.getCreatedAt();
        this.comments = comment.getComments();
        this.username = comment.getUsers().getUsername();
    }
}
