package com.odw.chat.model.vo;

public class Chat {
	
	private int chatNo;
	private String chatName;
	private String chatContent;
	private String chatCreateDate;
	private int memNo;
	private String memId;
	private int boardNo;
	private int memberCount;
	private int memberNow;
	private String loginUser;
	private String chatStatus;
	
	public String getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public Chat() {
		super();
	}

	public Chat(int chatNo, String chatName, String chatContent, String chatCreateDate, int memNo, String memId,
			int boardNo, int memberCount, int memberNow) {
		super();
		this.chatNo = chatNo;
		this.chatName = chatName;
		this.chatContent = chatContent;
		this.chatCreateDate = chatCreateDate;
		this.memNo = memNo;
		this.memId = memId;
		this.boardNo = boardNo;
		this.memberCount = memberCount;
		this.memberNow = memberNow;
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public String getChatCreateDate() {
		return chatCreateDate;
	}

	public void setChatCreateDate(String chatCreateDate) {
		this.chatCreateDate = chatCreateDate;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public int getMemberNow() {
		return memberNow;
	}

	public void setMemberNow(int memberNow) {
		this.memberNow = memberNow;
	}

	@Override
	public String toString() {
		return "Chat [chatNo=" + chatNo + ", chatName=" + chatName + ", chatContent=" + chatContent
				+ ", chatCreateDate=" + chatCreateDate + ", memNo=" + memNo + ", memId=" + memId + ", boardNo="
				+ boardNo + ", memberCount=" + memberCount + ", memberNow=" + memberNow + "]";
	}
	
	

}
