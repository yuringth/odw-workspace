package com.odw.board.model.vo;

public class Heart {
	private int memNo; //MEM_NO
	private int boardNo; //BOARD_NO
	private String heartYn; //HEART_YN
	public Heart() {
		super();
	}
	public Heart(int memNo, int boardNo, String heartYn) {
		super();
		this.memNo = memNo;
		this.boardNo = boardNo;
		this.heartYn = heartYn;
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
	public String getHeartYn() {
		return heartYn;
	}
	public void setHeartYn(String heartYn) {
		this.heartYn = heartYn;
	}
	@Override
	public String toString() {
		return "Heart [memNo=" + memNo + ", boardNo=" + boardNo + ", heartYn=" + heartYn + "]";
	}

	
}
