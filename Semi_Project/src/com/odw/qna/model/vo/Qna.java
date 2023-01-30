package com.odw.qna.model.vo;

import java.sql.Date;

public class Qna {
	
	private int qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private Date qnaCreateDate;
	private String qnaCheck;
	private String answerContent;
	private String memNo;
	
	
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Qna(int qnaNo, String qnaTitle, String qnaContent, Date qnaCreateDate, String qnaCheck, String answerContent,
			String memNo) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaCreateDate = qnaCreateDate;
		this.qnaCheck = qnaCheck;
		this.answerContent = answerContent;
		this.memNo = memNo;
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


	public String getAnswerContent() {
		return answerContent;
	}


	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}


	public String getMemNo() {
		return memNo;
	}


	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}


	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaCreateDate="
				+ qnaCreateDate + ", qnaCheck=" + qnaCheck + ", answerContent=" + answerContent + ", memNo=" + memNo
				+ "]";
	}
	
	
	
	

}
