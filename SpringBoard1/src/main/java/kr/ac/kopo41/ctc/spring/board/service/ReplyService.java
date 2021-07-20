package kr.ac.kopo41.ctc.spring.board.service;

import java.util.List;

import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;

public interface ReplyService {
	public List<Reply> findByBoardItem(BoardItem boardItem);
	public int countByBoardItemId(BoardItem boardItem);
	public void reply_insert(String boardItemId, String replyContent);
	public void reply_update(String replyId, String replyContent);
	public void reply_delete(String replyId);
}
