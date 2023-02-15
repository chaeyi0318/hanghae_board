package com.sparta.board.repository;

import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<BoardResponseDto> findAllByOrderByModifiedAtDesc();
    List<Board> findByIdOrderByModifiedAtDesc(Long id);
}
