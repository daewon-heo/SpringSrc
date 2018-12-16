package com.kh.spring.board.model.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardDate;
	private int readCount;
	//가상컬럼에 대한 필드추가
	private int fileCount;
	//첨부파일에 대한 필드추가
	private List<Attachment> files = new ArrayList<>();
	

	public Board() {}
	
	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, Date boardDate, int readCount,
			int fileCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.readCount = readCount;
		this.fileCount = fileCount;
	}
	
	//첨부파일필드 files를 추가한 생성자
	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, Date boardDate, int readCount,
			int fileCount, List<Attachment> files) {
		this(boardNo, boardTitle, boardWriter, boardContent, boardDate, readCount, fileCount);
		this.files = files;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public List<Attachment> getFiles() {
		return files;
	}

	public void setFiles(List<Attachment> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardDate=" + boardDate + ", readCount=" + readCount
				+ ", fileCount=" + fileCount + ", files=" + files + "]";
	}



	
	
	
	
	
}
