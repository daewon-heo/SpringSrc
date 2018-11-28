package com.kh.jsp.boardComment.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardComment implements Serializable {

	private int cno;
	private int bid;
	private String ccontent;
	private String cwriter;
	private Date cdate;
	private int refcno;
	private int clevel;
	
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardComment(int bid, String ccontent, String cwriter, int refcno, int clevel) {
		super();
		this.bid = bid;
		this.ccontent = ccontent;
		this.cwriter = cwriter;
		this.refcno = refcno;
		this.clevel = clevel;
	}

	public BoardComment(int cno, int bid, String ccontent, String cwriter, Date cdate, int refcno, int clevel) {
		super();
		this.cno = cno;
		this.bid = bid;
		this.ccontent = ccontent;
		this.cwriter = cwriter;
		this.cdate = cdate;
		this.refcno = refcno;
		this.clevel = clevel;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getRefcno() {
		return refcno;
	}

	public void setRefcno(int refcno) {
		this.refcno = refcno;
	}

	public int getClevel() {
		return clevel;
	}

	public void setClevel(int clevel) {
		this.clevel = clevel;
	}

	@Override
	public String toString() {
		return "BoardComment [cno=" + cno + ", bid=" + bid + ", ccontent=" + ccontent + ", cwriter=" + cwriter
				+ ", cdate=" + cdate + ", refcno=" + refcno + ", clevel=" + clevel + "]";
	}
}
