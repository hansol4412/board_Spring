package kr.ac.kopo41.ctc.spring.board.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;
import kr.ac.kopo41.ctc.spring.board.repository.BoardItemRepository;
import kr.ac.kopo41.ctc.spring.board.repository.BoardRepository;
import kr.ac.kopo41.ctc.spring.board.repository.ReplyRepository;

@Controller
public class BoardItemController {         
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	Date date = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    
	@RequestMapping(value = "/list") 
	public String list(HttpServletRequest request, Model model){
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
		
		for(BoardItem boardItem : boardItems) {
			boardItem.setCommentcnt((int)(replyRepository.countByBoardItemId(boardItem.getId())));
		}
		
		model.addAttribute("boardList", boardItems);
		model.addAttribute("boardId", boardId);
		model.addAttribute("date", sd.format(date));
		return "list";
	}
	
	@RequestMapping(value = "/view") 
	public String view(HttpServletRequest request, Model model){
		int bno = Integer.parseInt(request.getParameter( "key" ));
		BoardItem boardItem = boardItemRepository.findById(bno).get();
		List<Reply> replies = replyRepository.findByBoardItemOrderByIdDesc(boardItem);
		

		boardItem.setViewcnt(boardItem.getViewcnt()+1); //조회수 증가
		boardItemRepository.save(boardItem);
		
		
		model.addAttribute("board", boardItem);
		model.addAttribute("replyList", replies);
		return "view";
	}
	
	@RequestMapping(value = "/insert") 
	public String insert(HttpServletRequest request, Model model){
		String boardId = request.getParameter("boardId");
        model.addAttribute("date", sd.format(date));
        model.addAttribute("boardId", boardId);
		return "insert";
	}
	
	@RequestMapping(value = "/update") 
	public String update(HttpServletRequest request, Model model){
		int bno = Integer.parseInt(request.getParameter( "key" ));
		BoardItem boardItems = boardItemRepository.findById(bno).get();
		model.addAttribute("board", boardItems);
		return "update";
	}
	
	@RequestMapping(value = "/write") 
	public String write(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		String mode = request.getParameter( "key" ); 
		String id = request.getParameter( "id" ); 
        String title = request.getParameter( "title" ); 
        String date = request.getParameter("date"); 
        String content = request.getParameter( "content" ); 
        String boardId = request.getParameter( "boardId" ); 
        
		if(mode.equals("INSERT")){
			Board board = boardRepository.findById(Integer.parseInt(boardId)).get();
			BoardItem b = new BoardItem();
			b.setTitle(title);
			b.setContent(content);
			b.setBoard(board);
			b.setCommentcnt(0);
			b.setViewcnt(0);
			b.setDate(date);
			boardItemRepository.save(b);
        } else if(mode.equals("UPDATE")){
        	BoardItem boardItem = boardItemRepository.findById(Integer.parseInt(id)).get();
        	boardItem.setTitle(title);
        	boardItem.setContent(content);
        	boardItemRepository.save(boardItem);	
        } else if(mode.equals("DELETE")){
        	boardItemRepository.deleteById((Integer.parseInt(id)));
        }
		model.addAttribute("boardId", boardId);
		
		return "redirect:/list?boardId="+boardId;
	}
	
	
	
	

}
