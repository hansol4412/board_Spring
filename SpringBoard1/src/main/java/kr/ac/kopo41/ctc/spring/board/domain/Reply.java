package kr.ac.kopo41.ctc.spring.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String date;
	
	@Column
	private String content;
	
	@ManyToOne(optional=false)
	@JoinColumn//(name="boarditem_id")
	@JsonBackReference
	private BoardItem boardItem;
	
	
	public Integer getId() {
		return id;
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

	public BoardItem getBoardItem() {
		return boardItem;
	}

	public void setBoardItem(BoardItem boardItem) {
		this.boardItem = boardItem;
	}
	
}
