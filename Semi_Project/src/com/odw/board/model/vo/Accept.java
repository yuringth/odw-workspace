package com.odw.board.model.vo;

public class Accept {
	
	private int chatNo; //CHAT_NO	NUMBER
	private int memNo; //MEM_NO	NUMBER
	private String acceptYn; //ACCEPT_YN	CHAR(2 BYTE)
	private String applyYn; //APPLY_YN	CHAR(2 BYTE)
	private int boardNo; //BOARD_NO	NUMBER
	private String memId;
	
	public Accept() {
		super();
	}
	public Accept(int chatNo, int memNo, String acceptYn, String applyYn, int boardNo) {
		super();
		this.chatNo = chatNo;
		this.memNo = memNo;
		this.acceptYn = acceptYn;
		this.applyYn = applyYn;
		this.boardNo = boardNo;
	}
	
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getAcceptYn() {
		return acceptYn;
	}
	public void setAcceptYn(String acceptYn) {
		this.acceptYn = acceptYn;
	}
	public String getApplyYn() {
		return applyYn;
	}
	public void setApplyYn(String applyYn) {
		this.applyYn = applyYn;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	@Override
	public String toString() {
		return "Accept [chatNo=" + chatNo + ", memNo=" + memNo + ", acceptYn=" + acceptYn + ", applyYn=" + applyYn
				+ ", boardNo=" + boardNo + "]";
	}
	
	

}
