package com.odw.board.model.vo;

import java.sql.Date;

public class Qna {
	private int qnaNo; //QNA_NO	NUMBER
	private String qnaTitle; //QNA_TITLE	VARCHAR2(30 BYTE)
	private String qnaContent; //QNA_CONTENT	VARCHAR2(300 BYTE)
	private Date qnaCreateDate; //QNA_CREATE_DATE	DATE
	private String qnaCheck; //QNA_CHECK	CHAR(2 BYTE)
	private int memNo; //MEM_NO	NUMBER
	private String answerContent; //ANSWER_CONTENT	VARCHAR2(1000 BYTE)
	private String boardName;
	
	
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public Qna() {
		super();
	}
	public Qna(int qnaNo, String qnaTitle, String qnaContent, Date qnaCreateDate, String qnaCheck, int memNo,
			String answerContent) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaCreateDate = qnaCreateDate;
		this.qnaCheck = qnaCheck;
		this.memNo = memNo;
		this.answerContent = answerContent;
	}
	
	
	public Qna(int qnaNo, String qnaTitle, Date qnaCreateDate, String qnaCheck) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaCreateDate = qnaCreateDate;
		this.qnaCheck = qnaCheck;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Date getQnaCreateDate() {
		return qnaCreateDate;
	}
	public void setQnaCreateDate(Date qnaCreateDate) {
		this.qnaCreateDate = qnaCreateDate;
	}
	public String getQnaCheck() {
		return qnaCheck;
	}
	public void setQnaCheck(String qnaCheck) {
		this.qnaCheck = qnaCheck;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	@Override
	public String toString() {
		return "QNA [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaCreateDate="
				+ qnaCreateDate + ", qnaCheck=" + qnaCheck + ", memNo=" + memNo + ", answerContent=" + answerContent
				+ "]";
	}
	
	
}
