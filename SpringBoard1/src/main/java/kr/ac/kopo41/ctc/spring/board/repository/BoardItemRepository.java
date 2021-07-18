package kr.ac.kopo41.ctc.spring.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;

public interface BoardItemRepository extends JpaRepository<BoardItem, Integer>{
//	List<BoardItem> findByBoardOrderByIdDesc(Board board);
//
//	List<BoardItem> findByBoardAndTitleContainingOrderByIdDesc(Board board, String title);
//	
//	List<BoardItem> findByBoardAndContentContainingOrderByIdDesc(Board board, String content);
	
	Page<BoardItem> findByBoardOrderByIdDesc(Board board, Pageable pageable);

	Page<BoardItem> findByBoardAndTitleContainingOrderByIdDesc(Board board, String title, Pageable pageable);
	
	Page<BoardItem> findByBoardAndContentContainingOrderByIdDesc(Board board, String content, Pageable pageable);
	
	long countByBoardId(int boardid); 
	long countByBoardIdAndTitleContaining(int boardid, String title); 
	long countByBoardIdAndContentContaining(int boardid, String content); 
	
	
}
