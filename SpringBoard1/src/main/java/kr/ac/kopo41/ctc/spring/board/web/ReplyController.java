package kr.ac.kopo41.ctc.spring.board.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;
import kr.ac.kopo41.ctc.spring.board.repository.BoardItemRepository;
import kr.ac.kopo41.ctc.spring.board.repository.ReplyRepository;

@Controller
public class ReplyController {
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	Date date = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping(value = "/reply_write") 
	public String reply_write(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		String mode = request.getParameter( "key" ); 
        String boardItemId = request.getParameter( "gongjiId" ); 
        String replyId = request.getParameter( "replyId" ); 
        String replyContent = request.getParameter( "replyContent" ); 
        
        if(mode.equals("insert")){
        	BoardItem boardItem = boardItemRepository.findById(Integer.parseInt(boardItemId)).get();
        	Reply reply = new Reply();
        	reply.setContent(replyContent);
        	reply.setBoardItem(boardItem);
        	reply.setDate(sd.format(date));
        	replyRepository.save(reply);
        } else if(mode.equals("update")){
        	Reply reply = replyRepository.findById(Integer.parseInt(replyId)).get();
        	reply.setContent(replyContent);
        	replyRepository.save(reply);
        } else if(mode.equals("delete")){
        	replyRepository.deleteById(Integer.parseInt(replyId));
        }
		return "redirect:/view?key="+boardItemId;
	}
}
