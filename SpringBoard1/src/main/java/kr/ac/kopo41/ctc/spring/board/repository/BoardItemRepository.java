package kr.ac.kopo41.ctc.spring.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;

public interface BoardItemRepository extends JpaRepository<BoardItem, Integer>{
	List<BoardItem> findByBoardOrderByIdDesc(Board board);

	List<BoardItem> findByBoardAndTitleContainingOrderByIdDesc(Board board, String title);
	
	List<BoardItem> findByBoardAndContentContainingOrderByIdDesc(Board board, String content);
	
}
