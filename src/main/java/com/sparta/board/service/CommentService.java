package com.sparta.board.service;

import com.sparta.board.dto.CommentRequestDto;
import com.sparta.board.dto.CommentResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.Users;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    //댓글 등록
    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, Users users) {
        users = userRepository.findByUsername(users.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        Comment comment = commentRepository.save(new Comment(commentRequestDto, board, users));

        return new CommentResponseDto(comment);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, Users users) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if (!users.getUsername().equals(comment.getUsers().getUsername())) {
            throw new IllegalArgumentException("작성자가 다릅니다.");

        } else {
            comment.updateComment(commentRequestDto);
            return new CommentResponseDto(comment);
        }
    }

    //댓글 삭제
    @Transactional
    public String deleteComment(Long id, Users users) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );

        if (!users.getUsername().equals(comment.getUsers().getUsername())) {
            throw new IllegalArgumentException("작성자가 다릅니다.");
        } else {
            commentRepository.deleteById(id);
            return "댓글이 삭제됐습니다.";
        }
    }

    //댓글 리스트
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComment(Long boardId) {
        List<CommentResponseDto> commentList = commentRepository.findAllByboardId(boardId);
        return commentList;
    }
}
