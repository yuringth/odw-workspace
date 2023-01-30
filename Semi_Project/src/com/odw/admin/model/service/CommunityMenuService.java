package com.odw.admin.model.service;

import static com.odw.common.JDBCTemplate.close;
import static com.odw.common.JDBCTemplate.commit;
import static com.odw.common.JDBCTemplate.getConnection;
import static com.odw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.admin.model.dao.CommunityMenuDao;
import com.odw.admin.model.dao.InfoMenuDao;
import com.odw.admin.model.dao.MemberMenuDao;
import com.odw.attachment.model.vo.Attachment;
import com.odw.common.model.vo.PageInfo;
import com.odw.information.model.vo.Information;
import com.odw.notice.model.vo.Notice; 


public class CommunityMenuService {
	
	public int insertNotice(Notice notice, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new CommunityMenuDao().insertNotice(conn, notice);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new CommunityMenuDao().insertAttachment(conn, at);
		}
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	
	public int selectNoticeListCount(Notice notice) {
		Connection conn = getConnection();
		
		int listCount = new CommunityMenuDao().selectNoticeListCount(conn, notice);
		
		close(conn);
		
		return listCount;
	}
	
	
	
	public ArrayList<Notice> selectNoticeList(PageInfo pi, Notice notice){
		Connection conn = getConnection();
		
		ArrayList<Notice> noticeList = new CommunityMenuDao().selectNoticeList(conn, pi, notice);
		
		close(conn);
		
		return noticeList;
	}
	
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		
		Notice notice = new CommunityMenuDao().selectNotice(conn, noticeNo);
		
		close(conn);
		
		return notice;
	}
	
	public Attachment selectAttachment(int noticeNo) {
		Connection conn = getConnection();
		
		Attachment at = new CommunityMenuDao().selectAttachment(conn, noticeNo);
		
		close(conn);
		
		return at;
	}
	
	public int updateNotice(Notice notice, Attachment at, int noticeNo) {
		Connection conn = getConnection();
		
		int result1 = new CommunityMenuDao().updateNotice(conn, notice);
		
		int result2 = 1;
		
		if(at != null) {
			// 새로운 첨부파일 있다면
			if(at.getFileNo() != 0) {
				result2 = new CommunityMenuDao().updateAttachment(conn, at, noticeNo);
			} else {
				result2 = new CommunityMenuDao().insertNewAttachment(conn, at, noticeNo);
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
	
	public int deleteNoticeList(String[] noticeNoList) {
		Connection conn = getConnection();

		int[] resultList = new int[noticeNoList.length];
		
		int result = 0;
		
		for(int i = 0; i < noticeNoList.length; i++) {
			result = 1;
			
			resultList[i] = new CommunityMenuDao().deleteNoticeList(conn, Integer.parseInt(noticeNoList[i]));
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
	
	public int updateNoticeYn(int noticeNo) {
		Connection conn = getConnection();
		
		int result = new CommunityMenuDao().updateNoticeYn(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
}
