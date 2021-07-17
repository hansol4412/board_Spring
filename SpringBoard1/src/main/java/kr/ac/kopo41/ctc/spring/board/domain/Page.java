package kr.ac.kopo41.ctc.spring.board.domain;

public class Page {
	private int pageno; // 현재 페이지 번호
	private int prev_pageno; // 이전 페이지 번호
	private int page_sno; // 현재 그룹 시작 번호
	private int page_eno; // 현재 그룹 끝 번호
	private int next_pageno; //다음 페이지 번호
	
	
	public Page(int pageno, int prev_pageno, int page_sno, int page_eno, int next_pageno) {
		this.pageno = pageno;
		this.prev_pageno = prev_pageno;
		this.page_sno = page_sno;
		this.page_eno = page_eno;
		this.next_pageno = next_pageno;
	}
	

	public int getPageno() {
		return pageno;
	}
	
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	
	public int getPrev_pageno() {
		return prev_pageno;
	}
	
	public void setPrev_pageno(int prev_pageno) {
		this.prev_pageno = prev_pageno;
	}
	
	public int getPage_sno() {
		return page_sno;
	}
	
	public void setPage_sno(int page_sno) {
		this.page_sno = page_sno;
	}
	
	public int getPage_eno() {
		return page_eno;
	}
	
	public void setPage_eno(int page_eno) {
		this.page_eno = page_eno;
	}
	
	public int getNext_pageno() {
		return next_pageno;
	}
	
	public void setNext_pageno(int next_pageno) {
		this.next_pageno = next_pageno;
	}
}
