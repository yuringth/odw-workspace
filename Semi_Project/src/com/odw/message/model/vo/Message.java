package com.odw.message.model.vo;

import java.sql.Date;

public class Message {

	
	private int msgNo;
	private String msgContent;
	private String msgCreateDate;
	private String recipient;
	private String msgCheck;
	private String memNo; 
	
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Message(int msgNo, String msgContent, String msgCreateDate, String recipient, String msgCheck, String memNo) {
		super();
		this.msgNo = msgNo;
		this.msgContent = msgContent;
		this.msgCreateDate = msgCreateDate;
		this.recipient = recipient;
		this.msgCheck = msgCheck;
		this.memNo = memNo;
	}




	public int getMsgNo() {
		return msgNo;
	}


	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}


	public String getMsgContent() {
		return msgContent;
	}


	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}


	public String getMsgCreateDate() {
		return msgCreateDate;
	}


	public void setMsgCreateDate(String msgCreateDate) {
		this.msgCreateDate = msgCreateDate;
	}


	public String getRecipient() {
		return recipient;
	}


	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}


	public String getMsgCheck() {
		return msgCheck;
	}


	public void setMsgCheck(String msgCheck) {
		this.msgCheck = msgCheck;
	}


	public String getMemNo() {
		return memNo;
	}


	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}


	@Override
	public String toString() {
		return "Message [msgNo=" + msgNo + ", msgContent=" + msgContent + ", msgCreateDate=" + msgCreateDate
				+ ", recipient=" + recipient + ", msgCheck=" + msgCheck + ", memNo=" + memNo + "]";
	}
	
	

	
	
}
