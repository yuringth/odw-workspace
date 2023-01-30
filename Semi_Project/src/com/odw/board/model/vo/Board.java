package com.odw.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo; // BOARD_NO	NUMBER
	private int memNo; // MEM_NO	NUMBER
	private String boardName; // BOARD_NAME	VARCHAR2(30 BYTE)
	private String boardCategory; // BOARD_CATEGORY	VARCHAR2(20 BYTE)
	private String boardTitle; // BOARD_TITLE	VARCHAR2(50 BYTE)
	private String boardContent; // BOARD_CONTENT	VARCHAR2(1000 BYTE)
	private String boardFile; // BOARD_FILE	VARCHAR2(500 BYTE)
	private String boardThumbnail; // BOARD_THUMBNAIL	VARCHAR2(500 BYTE)
	private Date createDate; // CREATE_DATE	DATE
	private int boardCount; // BOARD_COUNT	NUMBER
	private String boardReportStatus; // BOARD_REPORT_STATUS	VARCHAR2(5 BYTE)
	private String boardDeleteStatus; // BOARD_DELETE_STATUS	VARCHAR2(5 BYTE)
	private String meterial; // METERIAL	VARCHAR2(50 BYTE)
	private String keyword; // KEYWORD	VARCHAR2(40 BYTE)
	private String dpDate; // DP_DATE	DATE
	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public String getReplyCreateContent() {
		return replyCreateContent;
	}


	public void setReplyCreateContent(String replyCreateContent) {
		this.replyCreateContent = replyCreateContent;
	}


	private int memberCount; // MEMBER_COUNT	NUMBER
	private String boardAnswerYN; // BOARD_ANSWER_YN	CHAR(2 BYTE)
	private int likeCount; // LIKE_COUNT	NUMBER
	private int apllyNo; // APLLY_NO	NUMBER
	private int memberNow;
	private String memId; // 추가
	private String gender; // 추가
	private String birthDate; // 추가
	private String titleImg; //추가
	private int fileNo;
	private int result; // 성공여부
	private String grade;
	private String replyContent;
	private String replyCreateContent;
	private String replyCreateDate;
	
	public String getReplyCreateDate() {
		return replyCreateDate;
	}


	public void setReplyCreateDate(String replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}


	public Board() {
		super();
	}
	

	public Board(int boardNo, int memNo, String boardName, String boardCategory, String boardTitle, String boardContent,
			String boardFile, String boardThumbnail, Date createDate, int boardCount, String boardReportStatus,
			String boardDeleteStatus, String meterial, String keyword, String dpDate, int memberCount,
			String boardAnswerYN, int likeCount, int apllyNo, int memberNow, String memId, String gender,
			String birthDate) {
		super();
		this.boardNo = boardNo;
		this.memNo = memNo;
		this.boardName = boardName;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardFile = boardFile;
		this.boardThumbnail = boardThumbnail;
		this.createDate = createDate;
		this.boardCount = boardCount;
		this.boardReportStatus = boardReportStatus;
		this.boardDeleteStatus = boardDeleteStatus;
		this.meterial = meterial;
		this.keyword = keyword;
		this.dpDate = dpDate;
		this.memberCount = memberCount;
		this.boardAnswerYN = boardAnswerYN;
		this.likeCount = likeCount;
		this.apllyNo = apllyNo;
		this.memberNow = memberNow;
		this.memId = memId;
		this.gender = gender;
		this.birthDate = birthDate;
	}


	public Board(int boardNo, int memNo, String boardName, String boardCategory, String boardTitle, String boardContent,
			String boardFile, String boardThumbnail, Date createDate, int boardCount, String boardReportStatus,
			String boardDeleteStatus, String meterial, String keyword, String dpDate, int memberCount,
			String boardAnswerYN, int likeCount, int apllyNo, int memberNow, String memId) {
		super();
		this.boardNo = boardNo;
		this.memNo = memNo;
		this.boardName = boardName;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardFile = boardFile;
		this.boardThumbnail = boardThumbnail;
		this.createDate = createDate;
		this.boardCount = boardCount;
		this.boardReportStatus = boardReportStatus;
		this.boardDeleteStatus = boardDeleteStatus;
		this.meterial = meterial;
		this.keyword = keyword;
		this.dpDate = dpDate;
		this.memberCount = memberCount;
		this.boardAnswerYN = boardAnswerYN;
		this.likeCount = likeCount;
		this.apllyNo = apllyNo;
		this.memberNow = memberNow;
		this.memId = memId;
	}
	

	public Board(int boardNo, int memNo, String boardName, String boardCategory, String boardTitle, String boardContent,
			String boardFile, String boardThumbnail, Date createDate, int boardCount, String boardReportStatus,
			String boardDeleteStatus, String meterial, String keyword, String dpDate, int memberCount,
			String boardAnswerYN, int likeCount, int apllyNo, int memberNow) {
		super();
		this.boardNo = boardNo;
		this.memNo = memNo;
		this.boardName = boardName;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardFile = boardFile;
		this.boardThumbnail = boardThumbnail;
		this.createDate = createDate;
		this.boardCount = boardCount;
		this.boardReportStatus = boardReportStatus;
		this.boardDeleteStatus = boardDeleteStatus;
		this.meterial = meterial;
		this.keyword = keyword;
		this.dpDate = dpDate;
		this.memberCount = memberCount;
		this.boardAnswerYN = boardAnswerYN;
		this.likeCount = likeCount;
		this.apllyNo = apllyNo;
		this.memberNow = memberNow;
	}
	
	
	public Board(int boardNo, String boardTitle, String boardName, String boardContent, Date createDate,
			int boardCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardName = boardName;
		this.boardContent = boardContent;
		this.createDate = createDate;
		this.boardCount = boardCount;
	}
	

	public Board(String boardName, String boardTitle, int memNo,  Date createDate,  String boardContent) {
		super();
		this.boardName = boardName;
		this.boardTitle = boardTitle;
		this.memNo = memNo;
		this.createDate = createDate;
		this.boardContent = boardContent;
	}
	
	public Board(int boardNo, String boardTitle, Date createDate, int boardCount, int likeCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.createDate = createDate;
		this.boardCount = boardCount;
		this.likeCount = likeCount;
	}
	
	public Board(int boardNo, String boardTitle, Date createDate, String boardAnswerYN) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.createDate = createDate;
		this.boardAnswerYN = boardAnswerYN;
	}
	
	
	public Board(int boardNo, String boardTitle, String dpDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.dpDate = dpDate;
	}

	
	
	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}

	public String getBoardThumbnail() {
		return boardThumbnail;
	}

	public void setBoardThumbnail(String boardThumbnail) {
		this.boardThumbnail = boardThumbnail;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public String getBoardReportStatus() {
		return boardReportStatus;
	}

	public void setBoardReportStatus(String boardReportStatus) {
		this.boardReportStatus = boardReportStatus;
	}

	public String getBoardDeleteStatus() {
		return boardDeleteStatus;
	}

	public void setBoardDeleteStatus(String boardDeleteStatus) {
		this.boardDeleteStatus = boardDeleteStatus;
	}

	public String getMeterial() {
		return meterial;
	}

	public void setMeterial(String meterial) {
		this.meterial = meterial;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDpDate() {
		return dpDate;
	}

	public void setDpDate(String dpDate) {
		this.dpDate = dpDate;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getBoardAnswerYN() {
		return boardAnswerYN;
	}

	public void setBoardAnswerYN(String boardAnswerYN) {
		this.boardAnswerYN = boardAnswerYN;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getApllyNo() {
		return apllyNo;
	}

	public void setApllyNo(int apllyNo) {
		this.apllyNo = apllyNo;
	}


	public int getMemberNow() {
		return memberNow;
	}

	public void setMemberNow(int memberNow) {
		this.memberNow = memberNow;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getTitleImg() {
	      return titleImg;
	}

   public void setTitleImg(String titleImg) {
	   	  this.titleImg = titleImg;
   }
   
   public int getFileNo() {
	   	  return fileNo;
   }


   public void setFileNo(int fileNo) {
          this.fileNo = fileNo;
   }
   
   

	public int getResult() {
	return result;
	}
	
	
	public void setResult(int result) {
		this.result = result;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", memNo=" + memNo + ", boardName=" + boardName + ", boardCategory="
				+ boardCategory + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardFile="
				+ boardFile + ", boardThumbnail=" + boardThumbnail + ", createDate=" + createDate + ", boardCount="
				+ boardCount + ", boardReportStatus=" + boardReportStatus + ", boardDeleteStatus=" + boardDeleteStatus
				+ ", meterial=" + meterial + ", keyword=" + keyword + ", dpDate=" + dpDate + ", memberCount="
				+ memberCount + ", boardAnswerYN=" + boardAnswerYN + ", likeCount=" + likeCount + ", apllyNo=" + apllyNo
				+ ", memberNow=" + memberNow + ", memId=" + memId + ", gender=" + gender + ", birthDate=" + birthDate
				+ "]";
	}

	

	
}
