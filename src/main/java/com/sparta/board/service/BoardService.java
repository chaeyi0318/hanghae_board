package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.dto.CommentResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Users;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.CommentRepository;
import com.sparta.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    //게시글 등록
    @Transactional
    public BoardResponseDto createPost(BoardRequestDto requestDto, Users users) {
        Board board = boardRepository.save(new Board(requestDto, users));
        return new BoardResponseDto(board);
    }

    //게시글 상세 조회
    @Transactional(readOnly = true)
    public BoardResponseDto getContents(Long id, List<CommentResponseDto> commentList) {
        Board board = boardRepository.findById(id).orElseThrow();
        return new BoardResponseDto(board, commentList);
    }

    //게시글 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, Users users) {
        users = userRepository.findByUsername(users.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if (users.getUsername().equals(board.getUsers().getUsername()) || users.getRole().equals("ROLE_ADMIN")) {
            board.update(requestDto);
            return new BoardResponseDto(board);
        } else {
            throw new IllegalArgumentException("userName이 다릅니다.");
        }
    }

    //게시글 삭제
    @Transactional
    public String deleteBoard(Long id, Users users) {
        users = userRepository.findByUsername(users.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        if (users.getUsername().equals(board.getUsers().getUsername()) || users.getRole().equals("ROLE_ADMIN")) {
            boardRepository.deleteById(id);
            return "게시글 삭제 성공";
        } else {
            return "userName이 다릅니다.";
        }
    }
}
