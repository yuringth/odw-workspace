package com.odw.notice.model.vo;

import java.sql.Date;

public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String boardName;
	private int memNo;
	private int noticeCount;
	private Date noticeCreateDate;
	private String deleteYn;
	private String noticeWriter;
	
	
	
	public Notice() {
		super();
	}



	

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String boardName, int memNo, int noticeCount,
			Date noticeCreateDate, String deleteYn, String noticeWriter) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.boardName = boardName;
		this.memNo = memNo;
		this.noticeCount = noticeCount;
		this.noticeCreateDate = noticeCreateDate;
		this.deleteYn = deleteYn;
		this.noticeWriter = noticeWriter;
	}





	public int getNoticeNo() {
		return noticeNo;
	}



	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}



	public String getNoticeTitle() {
		return noticeTitle;
	}



	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}



	public String getNoticeContent() {
		return noticeContent;
	}



	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}



	public String getBoardName() {
		return boardName;
	}



	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}



	public int getMemNo() {
		return memNo;
	}



	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}



	public int getNoticeCount() {
		return noticeCount;
	}



	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}



	public Date getNoticeCreateDate() {
		return noticeCreateDate;
	}



	public void setNoticeCreateDate(Date noticeCreateDate) {
		this.noticeCreateDate = noticeCreateDate;
	}



	public String getDeleteYn() {
		return deleteYn;
	}



	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	
	


	public String getNoticeWriter() {
		return noticeWriter;
	}



	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}



	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", boardName=" + boardName + ", memNo=" + memNo + ", noticeCount=" + noticeCount
				+ ", noticeCreateDate=" + noticeCreateDate + ", deleteYn=" + deleteYn + "]";
	}
	
	
	

	
}
