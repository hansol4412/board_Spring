package kr.ac.kopo41.ctc.spring.board.service;

import kr.ac.kopo41.ctc.spring.board.domain.Page;

public interface BoardItemService {
	public Page pagination(String pagenoP, String criteria, String findWord, String boardId);
}
