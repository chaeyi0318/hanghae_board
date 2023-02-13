package com.sparta.board.controller;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.entity.Board;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/boards")
    public List<Board> getBoard() {
        return boardService.getBoard();
    }

    @PostMapping("/boards")
    public Board createPost(@RequestBody BoardRequestDto requestDto) {
        return boardService.createPost(requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }

    //게시글 수정
    @PutMapping("/boards/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    //게시글 하나 조회
    @GetMapping("/boards/{id}")
    public List<Board> getContents(@PathVariable Long id) {
        return boardService.getContents(id);
    }
}
