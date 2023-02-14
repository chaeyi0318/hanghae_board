package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    //게시글 전체 조회
    @GetMapping("/posts")
    public List<Board> getBoard() {
        return boardService.getBoard();
    }

    //게시글 등록
    @PostMapping("/post")
    public BoardResponseDto createPost(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.createPost(requestDto, request);
    }

    //게시글 상세 조회
    @GetMapping("/post/{id}")
    public List<Board> getContents(@PathVariable Long id) {
        return boardService.getContents(id);
    }

    //게시글 수정
    @PutMapping("/post/{id}")
    public BoardResponseDto update(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.update(id, requestDto, request);
    }

    //게시글 삭제
    @DeleteMapping("/post/{id}")
    public String deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return boardService.deleteBoard(id, request);
    }
}
