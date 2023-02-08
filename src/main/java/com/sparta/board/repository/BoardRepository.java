package com.sparta.board.repository;

import com.sparta.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();

    List<Board> findByIdOrderByModifiedAtDesc(Long id);

//    Optional<Board> findByPwAndId(Long id,String pw);  //한개의 객체만 나옴, list 선언 수정
}
