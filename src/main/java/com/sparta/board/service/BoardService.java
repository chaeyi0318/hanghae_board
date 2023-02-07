package com.sparta.board.service;

import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BoardService {
    private BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }
}
