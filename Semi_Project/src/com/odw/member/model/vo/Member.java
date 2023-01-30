package com.odw.member.model.vo;

import java.sql.Date;

public class Member {
	private int memNo;	//MEM_NO	NUMBER
	private	String memId;	//MEM_ID VARCHAR2(16 BYTE)
	private String memPwd; //MEM_PWD	VARCHAR2(16 BYTE)
	private String memName; //MEM_NAME	VARCHAR2(10 BYTE)
	private String address; //ADDRESS	VARCHAR2(100 BYTE)
	private String addressDetail; //ADDRESS_DETAIL	VARCHAR2(50 BYTE)
	private String phone; //PHONE	VARCHAR2(18 BYTE)
	private String email; //EMAIL	VARCHAR2(50 BYTE)
	private String birthDate; //BIRTH_DATE	VARCHAR2(20 BYTE)
	private String gender; //GENDER	CHAR(2 BYTE)
	private String grade; //GRADE	VARCHAR2(30 BYTE)
	private Date enrollDate; //ENROLL_DATE	DATE
	private String stopYn; //STOP_YN	CHAR(2 BYTE)
	private Date stopDate; //STOP_DATE	DATE
	private String dropYn; //DROP_YN	CHAR(2 BYTE)
	private Date dropDate; //DROP_DATE	DATE
	private int repCount; //REP_COUNT	NUMBER
	
	
	
	public Member() {}
	
	
	
	public Member(int memNo, String memId, String memPwd, String memName, String address, String addressDetail,
			String phone, String email, String birthDate, String gender, String grade, Date enrollDate, String stopYn,
			Date stopDate, String dropYn, Date dropDate, int repCount) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.phone = phone;
		this.email = email;
		this.birthDate = birthDate;
		this.gender = gender;
		this.grade = grade;
		this.enrollDate = enrollDate;
		this.stopYn = stopYn;
		this.stopDate = stopDate;
		this.dropYn = dropYn;
		this.dropDate = dropDate;
		this.repCount = repCount;
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
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getStopYn() {
		return stopYn;
	}
	public void setStopYn(String stopYn) {
		this.stopYn = stopYn;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public String getDropYn() {
		return dropYn;
	}
	public void setDropYn(String dropYn) {
		this.dropYn = dropYn;
	}
	public Date getDropDate() {
		return dropDate;
	}
	public void setDropDate(Date dropDate) {
		this.dropDate = dropDate;
	}
	public int getRepCount() {
		return repCount;
	}
	public void setRepCount(int repCount) {
		this.repCount = repCount;
	}
	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memPwd=" + memPwd + ", memName=" + memName
				+ ", address=" + address + ", addressDetail=" + addressDetail + ", phone=" + phone + ", email=" + email
				+ ", birthDate=" + birthDate + ", gender=" + gender + ", grade=" + grade + ", enrollDate=" + enrollDate
				+ ", stopYn=" + stopYn + ", stopDate=" + stopDate + ", dropYn=" + dropYn + ", dropDate=" + dropDate
				+ ", repCount=" + repCount + "]";
	}
	
}
