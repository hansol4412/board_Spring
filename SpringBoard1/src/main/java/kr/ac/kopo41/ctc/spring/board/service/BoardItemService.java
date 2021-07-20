package kr.ac.kopo41.ctc.spring.board.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Page;

public interface BoardItemService {
	public BoardItem findById(int bno);
	
	public List<BoardItem> findByBoard(Board board, Page pageParameter);
	public List<BoardItem> findByBoardAndTitle(Board board, String find, Page pageParameter);
	public List<BoardItem> findByBoardAndContent(Board board, String find, Page pageParameter);
	
	public int countByBoardId(String boardId);
	public int countByBoardIdAndTitle(String boardId, String find);
	public int countByBoardIdAndContent(String boardId, String find);
	
	public void insert(String boardId, String title, String content, String date);
	public void update(String id, String title, String content);
	public void delete(String id);
	
	public void updateViewcnt(BoardItem boardItem);
	
	public Page pagination(String pagenoP, String criteria, String findWord, String boardId);
}
