package com.odw.report.model.vo;

import java.sql.Date;

public class Report {

	private int repNo; // REP_NO	NUMBER
	private String repReason; // REP_REASON	VARCHAR2(30 BYTE)
	private String repContent; // REP_CONTENT	VARCHAR2(1000 BYTE)
	private String repMemId; // REP_MEM_NO	NUMBER
	private String reportedMemId; // REPORTED_MEM_NO	NUMBER
	private Date repDate; // REP_DATE	DATE
	private String repYn; // REP_YN	CHAR(2 BYTE)
	private int boardNo; // BOARD_NO	NUMBER
	private int replyNo; // REPLY_NO	NUMBER
	private String boardContent; // 게시글 내용을 가져옴. 테이블에는 없는데 필요해서 제가 만들었어요
	private String replyContent; // 댓글 내용을 가져옴. 테이블에는 없는데 필요해서 제가 만들었습니다.
	private String boardDeleteYn; // 게시물이 숨겨졌는지 안 숨겨졌는지 숨겨졌으면 'Y', 안 숨겨졌으면 'N' / BOARD_DELETE_YN과 같습니다.
	private String replyDeleteYn; // 댓글이 숨겨졌는지 안 숨겨졌는지 숨겨졌으면 'Y', 안 숨겨졌으면 'N' / REPLY_DELETE_YN과 같습니다.
	
	public Report() {}

	public Report(int repNo, String repReason, String repContent, String repMemId, String reportedMemId, Date repDate,
			String repYn, int boardNo, int replyNo, String boardContent, String replyContent, String boardDeleteYn,
			String replyDeleteYn) {
		super();
		this.repNo = repNo;
		this.repReason = repReason;
		this.repContent = repContent;
		this.repMemId = repMemId;
		this.reportedMemId = reportedMemId;
		this.repDate = repDate;
		this.repYn = repYn;
		this.boardNo = boardNo;
		this.replyNo = replyNo;
		this.boardContent = boardContent;
		this.replyContent = replyContent;
		this.boardDeleteYn = boardDeleteYn;
		this.replyDeleteYn = replyDeleteYn;
	}

	public Report(int repNo, String repReason, String repContent, String repMemId, String reportedMemId, Date repDate,
			String repYn, String boardContent, String replyContent) {
		super();
		this.repNo = repNo;
		this.repReason = repReason;
		this.repContent = repContent;
		this.repMemId = repMemId;
		this.reportedMemId = reportedMemId;
		this.repDate = repDate;
		this.repYn = repYn;
		this.boardContent = boardContent;
		this.replyContent = replyContent;
	}

	public int getRepNo() {
		return repNo;
	}

	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}

	public String getRepReason() {
		return repReason;
	}

	public void setRepReason(String repReason) {
		this.repReason = repReason;
	}

	public String getRepContent() {
		return repContent;
	}

	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}

	public String getRepMemId() {
		return repMemId;
	}

	public void setRepMemId(String repMemId) {
		this.repMemId = repMemId;
	}

	public String getReportedMemId() {
		return reportedMemId;
	}

	public void setReportedMemId(String reportedMemId) {
		this.reportedMemId = reportedMemId;
	}

	public Date getRepDate() {
		return repDate;
	}

	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}

	public String getRepYn() {
		return repYn;
	}

	public void setRepYn(String repYn) {
		this.repYn = repYn;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getBoardDeleteYn() {
		return boardDeleteYn;
	}

	public void setBoardDeleteYn(String boardDeleteYn) {
		this.boardDeleteYn = boardDeleteYn;
	}

	public String getReplyDeleteYn() {
		return replyDeleteYn;
	}

	public void setReplyDeleteYn(String replyDeleteYn) {
		this.replyDeleteYn = replyDeleteYn;
	}

	@Override
	public String toString() {
		return "Report [repNo=" + repNo + ", repReason=" + repReason + ", repContent=" + repContent + ", repMemId="
				+ repMemId + ", reportedMemId=" + reportedMemId + ", repDate=" + repDate + ", repYn=" + repYn
				+ ", boardNo=" + boardNo + ", replyNo=" + replyNo + ", boardContent=" + boardContent + ", replyContent="
				+ replyContent + ", boardDeleteYn=" + boardDeleteYn + ", replyDeleteYn=" + replyDeleteYn + "]";
	}

	
		
}