package kr.ac.kopo41.ctc.spring.board.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.repository.BoardItemRepository;
import kr.ac.kopo41.ctc.spring.board.repository.BoardRepository;

@Controller
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;

	// 모든 게시판 리스트
	@RequestMapping(value = "/board/boardList") 
	@ResponseBody
	public List<Board> list(Model model){
		return boardRepository.findAll();
	}
	
	// 게시판 id로 찾기
	@RequestMapping(value = "/board/findId") 
	@ResponseBody
	public Optional<Board> findById(Model model){
		return boardRepository.findById(1);
	}

}
