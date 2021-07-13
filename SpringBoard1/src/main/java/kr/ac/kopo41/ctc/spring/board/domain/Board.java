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
import javax.persistence.OneToMany;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="board")
	private List<BoardItem> boardItems;
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<BoardItem> getBoardItems() {
		if(boardItems == null) {
			boardItems = new ArrayList<BoardItem>();
		}
		return boardItems;
	}
	
	public void setBoardItems(List<BoardItem> boardItems) {
		this.boardItems = boardItems;
	}
	
	public void addBoardItems(BoardItem b) {
		List<BoardItem> boarditems = getBoardItems();
		 boarditems.add(b);
	}
	
	@Override
	public String toString() {
		String result = "[Board_"+id+"] "+name;
		return result;
	}
	
}
