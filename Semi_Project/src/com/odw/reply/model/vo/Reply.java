package com.odw.reply.model.vo;

import java.sql.Date;

public class Reply {
	
	private int replyNo; //REPLY_NO	NUMBER
	private int memNo; //MEM_NO	NUMBER
	private int boardNo; //BOARD_NO	NUMBER
	private String replyContent; //REPLY_CONTENT	VARCHAR2(400 BYTE)
	private Date replyCreateDate; // REPLY_CREATE_DATE	DATE
	private String replyReportYn; // REPLY_REPORT_YN	CHAR(2 BYTE)
	private String replyDeleteYn; //REPLY_DELETE_YN	CHAR(2 BYTE)
	private String memId; // 추가 댓글작성자아이디
	private String createDate; // 추가 작성일(별칭)
	private String grade;
	private String loginUser;
	
	public Reply() {
		super();
	}

	public Reply(int replyNo, int memNo, int boardNo, String replyContent, Date replyCreateDate, String replyReportYn,
			String replyDeleteYn) {
		super();
		this.replyNo = replyNo;
		this.memNo = memNo;
		this.boardNo = boardNo;
		this.replyContent = replyContent;
		this.replyCreateDate = replyCreateDate;
		this.replyReportYn = replyReportYn;
		this.replyDeleteYn = replyDeleteYn;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyCreateDate() {
		return replyCreateDate;
	}

	public void setReplyCreateDate(Date replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}

	public String getReplyReportYn() {
		return replyReportYn;
	}

	public void setReplyReportYn(String replyReportYn) {
		this.replyReportYn = replyReportYn;
	}

	public String getReplyDeleteYn() {
		return replyDeleteYn;
	}

	public void setReplyDeleteYn(String replyDeleteYn) {
		this.replyDeleteYn = replyDeleteYn;
	}
	
	public String getMemId() {
	      return memId;
   }

   public void setMemId(String memId) {
      this.memId = memId;
   }

   public String getCreateDate() {
      return createDate;
   }

   public void setCreateDate(String createDate) {
      this.createDate = createDate;
   }   

   
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getLoginUser() {
		return loginUser;
	}
	
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", memNo=" + memNo + ", boardNo=" + boardNo + ", replyContent="
				+ replyContent + ", replyCreateDate=" + replyCreateDate + ", replyReportYn=" + replyReportYn
				+ ", replyDeleteYn=" + replyDeleteYn + "]";
	}
	
	
	
	
	
	

}
