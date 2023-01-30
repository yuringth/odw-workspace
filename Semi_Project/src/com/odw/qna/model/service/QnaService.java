package com.odw.qna.model.service;

import com.odw.qna.model.dao.QnaDao;
import com.odw.qna.model.vo.Qna;
import static com.odw.common.JDBCTemplate.*;

import java.sql.Connection;

public class QnaService {
	
	public int insertQna(Qna q) {
		
		Connection conn = getConnection();
		
		int result = new QnaDao().insertQna(conn, q);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	

}
