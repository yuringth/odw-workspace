package com.odw.admin.model.service;

import static com.odw.common.JDBCTemplate.close; 
import static com.odw.common.JDBCTemplate.commit;
import static com.odw.common.JDBCTemplate.getConnection;
import static com.odw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.admin.model.dao.InfoMenuDao;
import com.odw.admin.model.dao.MemberMenuDao;
import com.odw.attachment.model.vo.Attachment;
import com.odw.common.model.vo.PageInfo;
import com.odw.information.model.vo.Information;

public class InfoMenuService {

	public int selectSeasonListCount(Information info) {
		Connection conn = getConnection();
		
		int listCount = new InfoMenuDao().selectSeasonListCount(conn, info);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Information> selectSeasonInfoList(PageInfo pi, Information info){
		Connection conn = getConnection();
		
		ArrayList<Information> infoList = new InfoMenuDao().selectSeasonInfoList(conn, pi, info);
		
		close(conn);
		
		return infoList;
	}
	
	public int insertLocalInfo(Information info, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new InfoMenuDao().insertLocalInfo(conn, info);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new InfoMenuDao().insertAttachment(conn, at);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	public Information selectInformation(int infoNo) {
		Connection conn = getConnection();
		
		Information info = new InfoMenuDao().selectInformation(conn, infoNo);
		
		close(conn);
		
		return info;
	}
	
	public Attachment selectAttachment(int infoNo) {
		Connection conn = getConnection();
		
		Attachment at = new InfoMenuDao().selectAttachment(conn, infoNo);
		
		close(conn);
		
		return at;
	}
	
	public int updateLocalInfo(Information info, Attachment at, int infoNo) {
		Connection conn = getConnection();
		
		int result1 = new InfoMenuDao().updateLocalInfo(conn, info);
		
		int result2 = 1;
		
		if(at != null) {
			// 새로운 첨부파일 있다면
			if(at.getFileNo() != 0) {
				result2 = new InfoMenuDao().updateAttachment(conn, at, infoNo);
			} else {
				result2 = new InfoMenuDao().insertNewAttachment(conn, at, infoNo);
			}
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}
	
	public int insertBeginnerInfo(Information info, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new InfoMenuDao().insertBeginnerInfo(conn, info);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new InfoMenuDao().insertAttachment(conn, at);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	public int updateBeginnerInfo(Information info, Attachment at, int infoNo) {
		Connection conn = getConnection();
		
		int result1 = new InfoMenuDao().updateBeginnerInfo(conn, info);
		
		int result2 = 1;
		
		if(at != null) {
			// 새로운 첨부파일 있다면
			if(at.getFileNo() != 0) {
				result2 = new InfoMenuDao().updateAttachment(conn, at, infoNo);
				//System.out.println("들어왔나");
			} else {
				result2 = new InfoMenuDao().insertNewAttachment(conn, at, infoNo);
			}
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}

	public int insertSeasonInfo(Information info, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new InfoMenuDao().insertSeasonInfo(conn, info);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new InfoMenuDao().insertAttachment(conn, at);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	public int updateSeasonInfo(Information info, Attachment at, int infoNo) {
		Connection conn = getConnection();
		
		int result1 = new InfoMenuDao().updateSeasonInfo(conn, info);
		
		int result2 = 1;
		
		if(at != null) {
			// 새로운 첨부파일 있다면
			if(at.getFileNo() != 0) {
				result2 = new InfoMenuDao().updateAttachment(conn, at, infoNo);
				//System.out.println("들어왔나");
			} else {
				result2 = new InfoMenuDao().insertNewAttachment(conn, at, infoNo);
			}
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}
	
	public int deleteInfoList(String[] infoNoList){
		Connection conn = getConnection();

		int[] resultList = new int[infoNoList.length];
		
		int result = 0;
		
		for(int i = 0; i < infoNoList.length; i++) {
			result = 1;
			
			resultList[i] = new InfoMenuDao().deleteInfo(conn, Integer.parseInt(infoNoList[i]));
			result = result * resultList[i];
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteInfo(int infoNo) {
		Connection conn = getConnection();
		
		int result = new InfoMenuDao().deleteInfo(conn, infoNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int selectBeginnerListCount(String deleteYn) {
		Connection conn = getConnection();
		
		int listCount = new InfoMenuDao().selectBeginnerListCount(conn, deleteYn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Information> selectBeginnerInfoList(PageInfo pi, String deleteYn){
		Connection conn = getConnection();
		
		ArrayList<Information> infoList = new InfoMenuDao().selectBeginnerInfoList(conn, pi, deleteYn);
		
		close(conn);
		
		return infoList;
	}
	
	public int selectLocalListCount(Information info) {
		Connection conn = getConnection();
		
		int listCount = new InfoMenuDao().selectLocalListCount(conn, info);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Information> selectLocalInfoList(PageInfo pi, Information info){
		Connection conn = getConnection();
		
		ArrayList<Information> infoList = new InfoMenuDao().selectLocalInfoList(conn, pi, info);
		
		close(conn);
		
		return infoList;
	}
}
