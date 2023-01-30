package com.odw.report.model.service;

import static com.odw.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import static com.odw.common.JDBCTemplate.*;

import com.odw.report.model.dao.ReportDao;
import com.odw.report.model.vo.Report;

public class ReportService {
	
	
	public int insertReport(Report r) {
		
		Connection conn = getConnection();
		
		int result = new ReportDao().insertReport(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

}
