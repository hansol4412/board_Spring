package kr.ac.kopo41.ctc.spring.board.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;
import kr.ac.kopo41.ctc.spring.board.repository.BoardItemRepository;
import kr.ac.kopo41.ctc.spring.board.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	Date date = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    
	@Override
	public void reply_insert(String boardItemId, String replyContent) {
		BoardItem boardItem = boardItemRepository.findById(Integer.parseInt(boardItemId)).get();
    	Reply reply = new Reply();
    	reply.setContent(replyContent);
    	reply.setBoardItem(boardItem);
    	reply.setDate(sd.format(date));
    	replyRepository.save(reply);
	}

	@Override
	public void reply_update(String replyId, String replyContent) {
		Reply reply = replyRepository.findById(Integer.parseInt(replyId)).get();
    	reply.setContent(replyContent);
    	replyRepository.save(reply);
	}

	@Override
	public void reply_delete(String replyId) {
		replyRepository.deleteById(Integer.parseInt(replyId));
	}

}
