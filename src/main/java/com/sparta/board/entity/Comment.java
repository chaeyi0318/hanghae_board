package com.sparta.board.entity;

import com.sparta.board.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comments;

    public Comment(CommentRequestDto requestDto, Board board, String username) {
        this.username = username;
        this.comments = requestDto.getComments();
    }
}
