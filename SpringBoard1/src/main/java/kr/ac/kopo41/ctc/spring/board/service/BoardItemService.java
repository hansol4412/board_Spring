package kr.ac.kopo41.ctc.spring.board.service;

import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Page;

public interface BoardItemService {
	public BoardItem findById(int bno);
	
	public void insert(String boardId, String title, String content, String date);
	public void update(String id, String title, String content);
	public void delete(String id);
	
	public Page pagination(String pagenoP, String criteria, String findWord, String boardId);
}
