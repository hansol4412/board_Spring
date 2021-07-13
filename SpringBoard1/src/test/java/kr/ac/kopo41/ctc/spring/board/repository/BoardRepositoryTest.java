package kr.ac.kopo41.ctc.spring.board.repository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.ac.kopo41.ctc.spring.board.domain.Board;
import kr.ac.kopo41.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo41.ctc.spring.board.domain.Reply;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	//게시판 만들 때 게시판글도 추가
	//@Test 
	public void insert() {
		Board first = new Board();
		first.setName("공지게시판");
		
//		BoardItem b = new BoardItem();
//		b.setTitle("공지1");
//		b.setContent("공지사항1");
//		b.setBoard(first);
//		b.setCommentcnt(0);
//		b.setViewcnt(0);
//		b.setDate("2021-07-06");
//		first.addBoardItems(b);
		
//		BoardItem b1 = new BoardItem();
//		b1.setTitle("공지2");
//		b1.setContent("공지사항2");
//		b1.setBoard(first);
//		b1.setCommentcnt(0);
//		b1.setViewcnt(0);
//		b1.setDate("2021-07-06");
//		first.addBoardItems(b1);
		
		boardRepository.save(first);
	}
	
	//@Test
	public void boardList() {
		Optional<Board> findById = boardRepository.findById(2);
		Board board = findById.get();
		System.out.println(board);
		System.out.println("----------------------"+findById.toString());
		
		List<BoardItem> list = boardItemRepository.findAll();
		for(BoardItem bi : list) {
			System.out.println("---------------------->>"+bi.toString());
		}
	}
	
	//@Test
	public void boardItem() {
		Optional<BoardItem> findById = boardItemRepository.findById(3);
		BoardItem boarditem = findById.get();
		System.out.println(boarditem);
		System.out.println("---------------------->>>>>"+findById.toString());
	}
	
	
//	@Test
	public void delete() {
		//boardRepository.deleteById(2);
		boardItemRepository.deleteById(2);
	}
	
	
	
	//게시판 글 만들 때 댓글도 추가
	//@Test
	public void boarditem_reply() {
		Board board = boardRepository.findById(1).get();
		
		BoardItem b = new BoardItem();
		
		b.setTitle("공지1");
		b.setContent("공지사항1");
		b.setBoard(board);
		b.setCommentcnt(0);
		b.setViewcnt(0);
		b.setDate("2021-07-06");
		
		Reply reply = new Reply();
		reply.setBoardItem(b);
		reply.setContent("good");
		reply.setDate("2021-07-06");
		
		b.addReplys(reply);
		
		boardItemRepository.save(b);
	}
	
	//게시판 글 만들 때 댓글도 추가
	@Test
	public void reply() {
		
		BoardItem b = boardItemRepository.findById(1).get();
		
		Reply reply = new Reply();
		reply.setBoardItem(b);
		reply.setContent("good job");
		reply.setDate("2021-07-06");
		
		replyRepository.save(reply);

	}
	
	//@Test
	//@Transactional
	public void findAll() {
		List<Board> boards = boardRepository.findAll();
		for (Board board: boards) {
			//System.out.println(board.getId());
		}
	}
	
	
}
	
