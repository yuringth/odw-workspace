package com.odw.report.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static com.odw.common.JDBCTemplate.*;

import com.odw.report.model.vo.Report;

public class ReportDao {

	Properties prop = new Properties();
	
	public ReportDao() {
		
		String fileName = ReportDao.class.getResource("/sql/report/report-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public int insertReport(Connection conn, Report r) {
		
		// insert문 => dml문 => 트랜잭션처리 => 성공하면 1행이 추가됨
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReport");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 빈칸채우고 sql 실행
			pstmt.setInt(1, r.getBoardNo());
			pstmt.setString(2, r.getRepReason());
			pstmt.setString(3, r.getRepContent());
			pstmt.setString(4, r.getRepMemId()); 
			pstmt.setString(5, r.getReportedMemId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
