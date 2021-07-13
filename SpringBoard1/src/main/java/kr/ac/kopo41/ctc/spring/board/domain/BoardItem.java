package kr.ac.kopo41.ctc.spring.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BoardItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private String title;
	
	@Column
	private String date;
	
	@Column
	private String content;
	
	@Column
	private int viewcnt;
	
	@Column
	private int commentcnt;
	
	
	@ManyToOne(optional=false)
	@JoinColumn//(name="board_id")
	@JsonBackReference
	private Board board;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="boardItem")
	private List<Reply> replies;
	
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getCommentcnt() {
		return commentcnt;
	}

	public void setCommentcnt(int commentcnt) {
		this.commentcnt = commentcnt;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Reply> getReplies() {
		if(replies == null) {
			replies = new ArrayList<Reply>();
		}
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	public void addReplys(Reply r) {
		List<Reply> replies = getReplies();
		replies.add(r);
	}
	
	@Override
	public String toString() {
		String result = "[BoardItem_"+id+"] "+title;
		return result;
	}
	
	
}
