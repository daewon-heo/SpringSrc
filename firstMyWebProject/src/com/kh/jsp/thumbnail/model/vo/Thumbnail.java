package com.kh.jsp.thumbnail.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.jsp.board.model.vo.Board;

public class Thumbnail extends Board 
					   implements Serializable {

	private ArrayList<Attachment> attachments;
	
	public Thumbnail() {
		super();
	}

	public Thumbnail(int bid, int btype, int bno, String btitle, String bcontent, String bwriter, int bcount,
			String boardfile, Date bdate, String status) {
		super(bid, btype, bno, btitle, bcontent, bwriter, bcount, boardfile, bdate, status);
	}

	public Thumbnail(String btitle, String bcontent, String bwriter, String boardfile) {
		super(btitle, bcontent, bwriter, boardfile);
	}

	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Thumbnail [toString()=" + super.toString() + ", getBid()=" + getBid() + ", getBtype()=" + getBtype()
				+ ", getBno()=" + getBno() + ", getBtitle()=" + getBtitle() + ", getBcontent()=" + getBcontent()
				+ ", getBwriter()=" + getBwriter() + ", getBcount()=" + getBcount() + ", getBoardfile()="
				+ getBoardfile() + ", getBdate()=" + getBdate() + ", getStatus()=" + getStatus() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
