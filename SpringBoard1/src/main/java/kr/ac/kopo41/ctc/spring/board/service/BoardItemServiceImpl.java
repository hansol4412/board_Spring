package kr.ac.kopo41.ctc.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Page;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;
import kr.ac.kopo41.ctc.spring.board.repository.BoardItemRepository;
import kr.ac.kopo41.ctc.spring.board.repository.BoardRepository;

@Service
public class BoardItemServiceImpl implements BoardItemService{
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public BoardItem findById(int bno) {
		BoardItem boardItems = boardItemRepository.findById(bno).get();
		return boardItems;
	}
	
	@Override
	public List<BoardItem> findByBoard(Board board, Page pageParameter) {
		List<BoardItem> boardItems = boardItemRepository.findByBoardOrderByIdDesc(board, PageRequest.of(pageParameter.getPageno()-1,10)).getContent();
		return boardItems;
	}

	@Override
	public List<BoardItem> findByBoardAndTitle(Board board, String find, Page pageParameter) {
		List<BoardItem> boardItems =  boardItemRepository.findByBoardAndTitleContainingOrderByIdDesc(board,find, PageRequest.of(pageParameter.getPageno()-1,10)).getContent();
		return boardItems;
	}

	@Override
	public List<BoardItem> findByBoardAndContent(Board board, String find, Page pageParameter) {
		List<BoardItem> boardItems = boardItemRepository.findByBoardAndContentContainingOrderByIdDesc(board,find, PageRequest.of(pageParameter.getPageno()-1,10)).getContent();
		return boardItems;
	}
	
	@Override
	public int countByBoardId(String boardId) {
		int itemtotalcount = (int)boardItemRepository.countByBoardId(Integer.parseInt(boardId));
		return itemtotalcount;
	}

	@Override
	public int countByBoardIdAndTitle(String boardId, String find) {
		int itemtotalcount = (int)boardItemRepository.countByBoardIdAndTitleContaining(Integer.parseInt(boardId), find);
		return itemtotalcount;
	}

	@Override
	public int countByBoardIdAndContent(String boardId, String find) {
		int itemtotalcount = (int)boardItemRepository.countByBoardIdAndContentContaining(Integer.parseInt(boardId), find);
		return itemtotalcount;
	}

	@Override
	public void insert(String boardId, String title, String content, String date) {
		Board board = boardRepository.findById(Integer.parseInt(boardId)).get();
		BoardItem b = new BoardItem();
		b.setTitle(title);
		b.setContent(content);
		b.setBoard(board);
		b.setCommentcnt(0);
		b.setViewcnt(0);
		b.setDate(date);
		boardItemRepository.save(b);
	}

	@Override
	public void update(String id, String title, String content) {
		BoardItem boardItem = boardItemRepository.findById(Integer.parseInt(id)).get();
    	boardItem.setTitle(title);
    	boardItem.setContent(content);
    	boardItemRepository.save(boardItem);	
	}

	@Override
	public void delete(String id) {
		boardItemRepository.deleteById((Integer.parseInt(id)));
	}
	
	@Override
	public void updateViewcnt(BoardItem boardItem) {
		boardItem.setViewcnt(boardItem.getViewcnt()+1); //조회수 증가
		boardItemRepository.save(boardItem);
	}

	@Override
	public Page pagination(String pagenoP, String criteria, String findWord, String boardId) {
		int pageno = toInt(pagenoP);
		
		if(pageno<1) {// 현재 페이지
			pageno = 1;
		}

		int total_record = 0;
		
		if(criteria== null || findWord == null) {
			total_record=(int)boardItemRepository.countByBoardId(Integer.parseInt(boardId));
			if(total_record==0) {
				total_record = 1;
			}
		} else {
			if(criteria.equals("title")) {
				total_record = (int)boardItemRepository.countByBoardIdAndTitleContaining(Integer.parseInt(boardId), findWord);
			}else if(criteria.equals("content")) {
				total_record = (int)boardItemRepository.countByBoardIdAndContentContaining(Integer.parseInt(boardId), findWord);
			}
			if(total_record==0) {
				total_record = 1;
			}
		}
		
		int page_per_record_cnt = 10; // 페이지 당 레코드 수
		int group_per_page_cnt = 10; // 페이지 당 보여줄 번호 수

		int record_end_no = pageno * page_per_record_cnt; // 한 페이지 내에서 끝
		int record_start_no = record_end_no - (page_per_record_cnt - 1); // 한 페이지 내에서 시작
		if(record_end_no>total_record)
		{
			record_end_no = total_record;
		}

		int total_page = total_record / page_per_record_cnt + (total_record % page_per_record_cnt > 0 ? 1 : 0); // 총 페이지 수
		if(pageno>total_page)
		{
			pageno = total_page;
		}

		int group_no = pageno / group_per_page_cnt + (pageno % group_per_page_cnt > 0 ? 1 : 0);
		// 현재 그룹번호 = 현재페이지 / 페이지당 보여줄 번호수 (현재 페이지 % 페이지당 보여줄 번호 수 >0 ? 1:0)

		int page_eno = group_no * group_per_page_cnt;
		// 현재 그룹 끝 번호 = 현재 그룹번호 * 페이지당 보여줄 번호
		int page_sno = page_eno - (group_per_page_cnt - 1);
		// 현재 그룹 시작 번호 = 현재 그룹 끝 번호 - (페이지당 보여줄 번호 수 -1)

		if(page_eno>total_page)
		{
			// 현재 그룹 끝 번호가 전체페이지 수 보다 클 경우
			page_eno = total_page;
			// 현재 그룹 끝 번호와 = 전체페이지 수를 같게
		}

		int prev_pageno = page_sno - group_per_page_cnt; // 이전 페이지 번호 = 현재 그룹 시작 번호 - 페이지당 보여줄 번호수

		int next_pageno = page_sno + group_per_page_cnt; // 다음 페이지 번호 = 현재 그룹 시작 번호 + 페이지당 보여줄 번호수

		if(prev_pageno<1) {
			// 이전 페이지 번호가 1보다 작을 경우
			prev_pageno = 1;
			// 이전 페이지를 1로
		}
		
		if(next_pageno>total_page){ // 다음 페이지보다 전체페이지 수보가 클경우
			next_pageno = total_page / group_per_page_cnt * group_per_page_cnt + 1;
			// 다음 페이지 = 전체페이지수 / 페이지당 보여줄 번호수 * 페이지당 보여줄 번호수 + 1
		}
		
		Page page = new Page(pageno, prev_pageno, page_sno, page_eno, next_pageno);
		return page;
	}
	
	//pagination에서 시용하는 메소드
	public int toInt(String x) {
		int a = 0;
		try {
			a = Integer.parseInt(x);
		} catch (Exception e) {
		}
		return a;
	}
}
