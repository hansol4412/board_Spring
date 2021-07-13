package kr.ac.kopo41.ctc.spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.kopo41.ctc.spring.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
}
