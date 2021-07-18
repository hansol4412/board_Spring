package kr.ac.kopo41.ctc.spring.board.service;

public interface ReplyService {
	public void reply_insert(String boardItemId, String replyContent);
	public void reply_update(String replyId, String replyContent);
	public void reply_delete(String replyId);
}
