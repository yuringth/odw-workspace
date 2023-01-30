package com.odw.information.model.vo;

import java.sql.Date;

public class Information {
	private int infoNo; // INFO_NO	NUMBER
	private String infoType; // INFO_TYPE	VARCHAR2(20 BYTE)
	private String type; // TYPE	VARCHAR2(250 BYTE)
	private String infoWriterNo; // INFO_WRITER_NO	NUMBER
	private String infoTitle; // INFO_TITLE	VARCHAR2(100 BYTE)
	private String infoContent; // INFO_CONTENT	VARCHAR2(1000 BYTE)
	private String infoMedia; // INFO_MEDIA	VARCHAR2(500 BYTE)
	private Date infoCreateDate; // INFO_CREATE_DATE	DATE
	private int infoCount; //INFO_COUNT	NUMBER
	private String infoDeleteYn; // INFO_DELETE_YN	CHAR(2 BYTE)
	private String season; // SEASON	VARCHAR2(10 BYTE)
	private String local; // LOCAL	VARCHAR2(250 BYTE)
	private int turnAround; // TURNAROUND	NUMBER
	private String course; // COURSE	VARCHAR2(500 BYTE)
	private String traffic; //TRAFFIC	VARCHAR2(500 BYTE)
	
	
	public Information() {}

	public Information(int infoNo, String infoType, String type, String infoWriterNo, String infoTitle,
			String infoContent, String infoMedia, Date infoCreateDate, int infoCount, String infoDeleteYn,
			String season, String local, int turnAround, String course, String traffic) {
		super();
		this.infoNo = infoNo;
		this.infoType = infoType;
		this.type = type;
		this.infoWriterNo = infoWriterNo;
		this.infoTitle = infoTitle;
		this.infoContent = infoContent;
		this.infoMedia = infoMedia;
		this.infoCreateDate = infoCreateDate;
		this.infoCount = infoCount;
		this.infoDeleteYn = infoDeleteYn;
		this.season = season;
		this.local = local;
		this.turnAround = turnAround;
		this.course = course;
		this.traffic = traffic;
	}

	public int getInfoNo() {
		return infoNo;
	}

	public void setInfoNo(int infoNo) {
		this.infoNo = infoNo;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInfoWriterNo() {
		return infoWriterNo;
	}

	public void setInfoWriterNo(String infoWriterNo) {
		this.infoWriterNo = infoWriterNo;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public String getInfoMedia() {
		return infoMedia;
	}

	public void setInfoMedia(String infoMedia) {
		this.infoMedia = infoMedia;
	}

	public Date getInfoCreateDate() {
		return infoCreateDate;
	}

	public void setInfoCreateDate(Date infoCreateDate) {
		this.infoCreateDate = infoCreateDate;
	}

	public int getInfoCount() {
		return infoCount;
	}

	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}

	public String getInfoDeleteYn() {
		return infoDeleteYn;
	}

	public void setInfoDeleteYn(String infoDeleteYn) {
		this.infoDeleteYn = infoDeleteYn;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(int turnAround) {
		this.turnAround = turnAround;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	@Override
	public String toString() {
		return "Information [infoNo=" + infoNo + ", infoType=" + infoType + ", type=" + type + ", infoWriterNo="
				+ infoWriterNo + ", infoTitle=" + infoTitle + ", infoContent=" + infoContent + ", infoMedia="
				+ infoMedia + ", infoCreateDate=" + infoCreateDate + ", infoCount=" + infoCount + ", infoDeleteYn="
				+ infoDeleteYn + ", season=" + season + ", local=" + local + ", turnAround=" + turnAround + ", course="
				+ course + ", traffic=" + traffic + "]";
	}
	
	
	
}
