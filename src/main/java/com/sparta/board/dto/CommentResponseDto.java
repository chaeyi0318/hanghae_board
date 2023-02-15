package com.sparta.board.dto;

import com.sparta.board.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String comments;

    public CommentResponseDto(Comment comment) {
        this.comments = comment.getComments();
    }
}
