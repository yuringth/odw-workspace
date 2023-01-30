package com.odw.information.model.service;

import static com.odw.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.information.model.dao.InformationDao;
import com.odw.information.model.vo.Information;


public class InformationService {

	public ArrayList<Information> selectInformation() {
		
		Connection conn = getConnection();
		
		ArrayList<Information> list = new InformationDao().selectInformation(conn);
		
		close(conn);
		
		return list;
		
	}

	

}
