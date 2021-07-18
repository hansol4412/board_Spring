package kr.ac.kopo41.ctc.spring.board.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.kopo41.ctc.spring.board.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping(value = "/reply_write") 
	public String reply_write(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		String mode = request.getParameter( "key" ); 
        String boardItemId = request.getParameter( "gongjiId" ); 
        String replyId = request.getParameter( "replyId" ); 
        String replyContent = request.getParameter( "replyContent" ); 
        
        if(mode.equals("insert")){
        	replyService.reply_insert(boardItemId, replyContent);
        } else if(mode.equals("update")){
        	replyService.reply_update(replyId, replyContent);
        } else if(mode.equals("delete")){
        	replyService.reply_delete(replyId);
        }
		return "redirect:/view?key="+boardItemId;
	}
}
