package com.sparta.board.repository;

import com.sparta.board.entity.Board;

import java.util.List;


public interface BoardRepository {
    List<Board> findAllByOrderByModifiedAtDesc();
}
