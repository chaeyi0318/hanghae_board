package com.sparta.board.controller;

import com.sparta.board.entity.Board;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView();
    }

    @GetMapping("/api/boards")
    public List<Board> getBoard() {
        return boardService.getBoard();
    }
}
