package com.sparta.board.service;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.User;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.repository.BoardRepository;
import com.sparta.board.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    //게시글 등록
    @Transactional
    public BoardResponseDto createPost(BoardRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.saveAndFlush(new Board(requestDto, user.getUsername()));

            return new BoardResponseDto(board);
        } else {
            return null;
        }
    }

    //게시글 상세 조회
    @Transactional(readOnly = true)
    public List<Board> getContents(Long id) {
        return boardRepository.findByIdOrderByModifiedAtDesc(id);
    }

    //게시글 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            if (claims.getSubject().equals(board.getUsername())) {
                board.update(requestDto);
                return new BoardResponseDto(board);
            } else {
                throw new IllegalArgumentException("userName이 다릅니다.");
            }
        } else {
            return null;
        }
    }

    //게시글 삭제
    @Transactional
    public String deleteBoard(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            if (claims.getSubject().equals(board.getUsername())) {
                boardRepository.deleteById(id);
                return "게시글 삭제 성공";
            } else {
                return "userName이 다릅니다.";
            }
        }
        return "토큰이 없습니다.";
    }
}
