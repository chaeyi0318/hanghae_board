package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.dto.CommentResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.entity.Comment;
import com.sparta.board.service.BoardService;
import com.sparta.board.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    //게시글 전체 조회
    @GetMapping("/posts")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    //게시글 등록
    @PostMapping("/post")
    public BoardResponseDto createPost(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.createPost(requestDto, request);
    }

    //게시글 상세 조회
    @GetMapping("/post/{id}")
    public List<BoardResponseDto> getContents(@PathVariable Long id) {
        List<CommentResponseDto> commentList = commentService.commentsList(id);
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
