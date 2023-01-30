package com.odw.attachment.model.vo;

import java.sql.Date;

public class Attachment {

	private int fileNo; //FILE_NO	NUMBER
	private String originName; // ORIGIN_NAME	VARCHAR2(30 BYTE)
	private String changeName; // CHAGE_NAME	VARCHAR2(30 BYTE)
	private String filePath; // FILE_PATH	VARCHAR2(50 BYTE)
	private Date createDate; // CREATE_DATE	DATE
	private String boardName; // BOARD_NAME	VARCHAR2(50 BYTE)
	private String status; // STATUS	VARCHAR2(5 BYTE)
	private int boardNo; // BOARD_NO	NUMBER
	private int infoNo; //INFO_NO	NUMBER
	private int noticeNo;
	
	public Attachment() {
		super();
	}

	public Attachment(int fileNo, String originName, String changeName, String filePath, Date createDate,
			String boardName, String status, int boardNo, int infoNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.createDate = createDate;
		this.boardName = boardName;
		this.status = status;
		this.boardNo = boardNo;
		this.infoNo = infoNo;
	}

	public Attachment(int fileNo, String originName, String changeName, String filePath) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getInfoNo() {
		return infoNo;
	}
	public void setInfoNo(int infoNo) {
		this.infoNo = infoNo;
	}
	
	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", createDate=" + createDate + ", boardName=" + boardName + ", status="
				+ status + ", boardNo=" + boardNo + ", infoNo=" + infoNo + "]";
	}
	
	
	
	
	
	
	
}
