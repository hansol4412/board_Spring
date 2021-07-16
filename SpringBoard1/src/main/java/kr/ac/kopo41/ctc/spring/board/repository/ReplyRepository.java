package kr.ac.kopo41.ctc.spring.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	List<Reply> findByBoardItemOrderByIdDesc(BoardItem boardItem);
	
	long countByBoardItemId(int boardItemid); 
}
