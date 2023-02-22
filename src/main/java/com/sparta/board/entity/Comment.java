package com.sparta.board.entity;

import com.sparta.board.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    public Comment(CommentRequestDto requestDto, Board board, Users users) {
        this.users = users;
        this.board = board;
        this.comments = requestDto.getComments();
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        this.comments = commentRequestDto.getComments();
    }
}
