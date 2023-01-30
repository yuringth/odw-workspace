package com.odw.admin.model.service;

import static com.odw.common.JDBCTemplate.close;
import static com.odw.common.JDBCTemplate.commit;
import static com.odw.common.JDBCTemplate.getConnection;
import static com.odw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.admin.model.dao.MemberMenuDao;
import com.odw.common.model.vo.PageInfo;
import com.odw.member.model.vo.Member;
import com.odw.qna.model.vo.Qna;
import com.odw.report.model.vo.Report;

public class MemberMenuService {
	
	public int selectMemberListCountByColumn(String column) {
		Connection conn = getConnection();
		
		int listCount = new MemberMenuDao().selectMemberListCountByColumn(conn, column);
		
		close(conn);
		
		return listCount;
	}
	
	public int selectMemberListCountByCondition(String condition) {
		Connection conn = getConnection();
		
		int listCount = new MemberMenuDao().selectMemberListCountByCondition(conn, condition);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Member> selectMemberList(PageInfo pi, String condition){
		Connection conn = getConnection();
		
		ArrayList<Member> memberList = new MemberMenuDao().selectMemberList(conn, pi, condition);
		
		close(conn);
		
		return memberList;
	}
	
	public ArrayList<Qna> selectQnaList(PageInfo pi, String qnaCheck) {
		Connection conn = getConnection();
		
		ArrayList<Qna> qnaList = new MemberMenuDao().selectQnaList(conn, pi, qnaCheck);
		
		close(conn);
		
		return qnaList;
	}
	
	public ArrayList<Report> selectReportList(PageInfo pi, String table, String repYn){
		Connection conn = getConnection();
		
		ArrayList<Report> reportList = new MemberMenuDao().selectReportList(conn, pi, table, repYn);
		
		close(conn);
		
		return reportList;
	}
	
	public Report selectReport(int reportNo) {
		Connection conn = getConnection();
		
		Report report = new MemberMenuDao().selectReport(conn, reportNo);
		
		close(conn);
		
		return report;
	}

	public int updateRepYn(int repNo) {
		Connection conn = getConnection();
		
		int result = new MemberMenuDao().updateRepYn(conn, repNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateBoardStatus(int boardNo, String deleteYn) {
		Connection conn = getConnection();
		
		int result = new MemberMenuDao().updateBoardStatus(conn, boardNo, deleteYn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateReplyStatus(int replyNo, String deleteYn) {
		Connection conn = getConnection();
		
		int result = new MemberMenuDao().updateReplyStatus(conn, replyNo, deleteYn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteMemberByMemId(String userId, int repNo) {
		Connection conn = getConnection();
		
		int result1 =  new MemberMenuDao().updateRepYn(conn, repNo);
//		System.out.println(result1);
		
		int result2 = new MemberMenuDao().deleteMemberByMemId(conn, userId);
//		System.out.println(result2);
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result1*result2;
	}
	
	public Qna selectQna(int qnaNo) {
		Connection conn = getConnection();
		
		Qna qna = new MemberMenuDao().selectQna(conn, qnaNo);
		
		close(conn);
		
		return qna;
	}
	
	public int updateQnaAnswer(int qnaNo, String answerContent) {
		Connection conn = getConnection();
		
		int result = new MemberMenuDao().updateQnaAnswer(conn, qnaNo, answerContent);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int selectSearchListCount(String keyword, String condition) {
		Connection conn = getConnection();
		
		int listCount = new MemberMenuDao().selectSearchListCount(conn, keyword, condition);
		
		if(listCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Member> selectMemListByKeyword(String keyword, String condition){
		Connection conn = getConnection();
		
		ArrayList<Member> list = new MemberMenuDao().selectMemListByKeyword(conn, keyword, condition);
		
		close(conn);
		
		return list;
	}
	
	public int deleteQnaAnswer(String[] qnaNoList) {
		Connection conn = getConnection();

		int[] resultList = new int[qnaNoList.length];
		
		int result = 0;
		
		for(int i = 0; i < qnaNoList.length; i++) {
			result = 1;
			
			resultList[i] = new MemberMenuDao().deleteQnaAnswer(conn, Integer.parseInt(qnaNoList[i]));
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
	
	public Member selectMember(int memNo) {
		Connection conn = getConnection();
		
		Member mem = new MemberMenuDao().selectMember(conn, memNo);
		
		close(conn);
		
		return mem;
	}
	
	public int updateMemberInfo(Member mem) {
		Connection conn = getConnection();
		
		int result = new MemberMenuDao().updateMemberInfo(conn, mem);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int resetMemPwd(int memNo) {
		Connection conn = getConnection();
		
		int result = new MemberMenuDao().resetMemPwd(conn, memNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int selectQnaListCountByQnaCheck(String qnaCheck) {
		Connection conn = getConnection();
		
		int listCount = new MemberMenuDao().selectQnaListCountByQnaCheck(conn, qnaCheck);
		
		close(conn);
		
		return listCount;
	}
	
	public int selectReportCountList(String table, String repYn) {
		Connection conn = getConnection();
		
		int listCount = new MemberMenuDao().selectReportCountList(conn, table, repYn);
		
		close(conn);
		
		return listCount;
	}
}
