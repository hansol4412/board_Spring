package kr.ac.kopo41.ctc.spring.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.repository.BoardItemRepository;
import kr.ac.kopo41.ctc.spring.board.repository.BoardRepository;

@Controller
public class BoardItemController {
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private BoardRepository boardRepository;
		
	@RequestMapping(value = "/list") 
	public String listByBoardId(HttpServletRequest request, Model model){
		String boardId = request.getParameter("boardId");
		String criteria = request.getParameter( "criteria" );
        String find = request.getParameter("find");
        
		Board board = boardRepository.findById(Integer.parseInt(boardId)).get();
		List<BoardItem> boardItems = null;
		
		if(criteria==null && find==null) {
			boardItems = boardItemRepository.findByBoardOrderByIdDesc(board);
		} else if (criteria.equals("title")){
			boardItems = boardItemRepository.findByBoardAndTitleContainingOrderByIdDesc(board,find);
		} else if (criteria.equals("content")){
			boardItems = boardItemRepository.findByBoardAndContentContainingOrderByIdDesc(board,find);
		}
		
		model.addAttribute("boardList", boardItems);
		model.addAttribute("boardId", boardId);
		return "list";
	}
	
	
	
	

}
