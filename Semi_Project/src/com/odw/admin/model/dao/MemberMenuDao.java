package com.odw.admin.model.dao;

import static com.odw.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.common.model.vo.PageInfo;
import com.odw.member.model.vo.Member;
import com.odw.qna.model.vo.Qna;
import com.odw.report.model.vo.Report;


public class MemberMenuDao {
	
	private Properties prop = new Properties();
	
	public MemberMenuDao() {
		String fileName = MemberMenuDao.class.getResource("/sql/admin/memberMenu-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int selectMemberListCountByColumn(Connection conn, String column) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT COUNT(*) FROM " + column;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public int selectMemberListCountByCondition(Connection conn, String condition) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMemberListCountByCondition");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//System.out.println(condition);
			
			pstmt.setString(1, condition);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	
	
	public ArrayList<Qna> selectQnaList(Connection conn, PageInfo pi, String qnaCheck){
		ArrayList<Qna> qnaList = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectQnaList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, qnaCheck);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				qnaList.add(new Qna(rset.getInt("QNA_NO"),
									rset.getString("QNA_TITLE"),
									rset.getString("QNA_CONTENT"),
									rset.getDate("QNA_CREATE_DATE"),
									rset.getString("QNA_CHECK"),
									rset.getString("ANSWER_CONTENT"),
									rset.getString("MEM_ID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		//System.out.println(qnaList.size());
		return qnaList;
	}
	
	public ArrayList<Report> selectReportList(Connection conn, PageInfo pi, String table, String repYn) {
		ArrayList<Report> reportList = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String sql = "SELECT * FROM(SELECT ROWNUM RNUM, A.* FROM "
					+ "(SELECT REP_NO, REP_REASON, REP_CONTENT, REP_MEM_ID, REPORTED_MEM_ID, "
				    + "REP_DATE, REP_YN, BOARD_CONTENT, REPLY_CONTENT "
				    + "FROM REPORT R LEFT JOIN BOARD B ON(R.BOARD_NO = B.BOARD_NO) "
	                + "LEFT JOIN REPLY USING(REPLY_NO) "
	                + "WHERE REP_YN LIKE '" + repYn + "' "
	                + "AND " + table + " LIKE '%' "
				    + "ORDER BY REP_NO DESC) A) "
					+ "WHERE RNUM BETWEEN " + startRow + " AND " + endRow;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reportList.add(new Report(rset.getInt("REP_NO"),
										  rset.getString("REP_REASON"),
										  rset.getString("REP_CONTENT"),
										  rset.getString("REP_MEM_ID"),
										  rset.getString("REPORTED_MEM_ID"),
										  rset.getDate("REP_DATE"),
										  rset.getString("REP_YN"),
										  rset.getString("BOARD_CONTENT"),
										  rset.getString("REPLY_CONTENT")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		return reportList;
	}
	
	public Report selectReport(Connection conn, int reportNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReport");
		
		Report report = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reportNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				report = new Report(rset.getInt("REP_NO"),
						  rset.getString("REP_REASON"),
						  rset.getString("REP_CONTENT"),
						  rset.getString("REP_MEM_ID"),
						  rset.getString("REPORTED_MEM_ID"),
						  rset.getDate("REP_DATE"),
						  rset.getString("REP_YN"),
						  rset.getInt("BOARD_NO"),
						  rset.getInt("REPLY_NO"),
						  rset.getString("BOARD_CONTENT"),
						  rset.getString("REPLY_CONTENT"),
						  rset.getString("BOARD_DELETE_STATUS"),
						  rset.getString("REPLY_DELETE_YN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return report;
	}
	
	public int updateRepYn(Connection conn, int repNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateRepYn");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoardStatus(Connection conn, int boardNo, String deleteYn) {
	
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoardStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deleteYn);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateReplyStatus(Connection conn, int replyNo, String deleteYn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReplyStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deleteYn);
			pstmt.setInt(2, replyNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteMemberByMemId(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMemberByMemId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Qna selectQna(Connection conn, int qnaNo) {
		Qna qno = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectQna");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qno = new Qna();
				qno.setQnaNo(rset.getInt("QNA_NO"));
				qno.setQnaTitle(rset.getString("QNA_TITLE"));
				qno.setQnaContent(rset.getString("QNA_CONTENT"));
				qno.setQnaCreateDate(rset.getDate("QNA_CREATE_DATE"));
				qno.setQnaCheck(rset.getString("QNA_CHECK"));
				qno.setMemNo(rset.getString("MEM_ID"));
				qno.setAnswerContent(rset.getString("ANSWER_CONTENT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return qno;
	}
	
	public int updateQnaAnswer(Connection conn, int qnaNo, String answerContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateQnaAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answerContent);
			pstmt.setInt(2, qnaNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Member> selectMemberList(Connection conn, PageInfo pi, String condition) {
		ArrayList<Member> memberList = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			//System.out.println(condition);
			
			pstmt.setString(1, condition);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member mem = new Member();
				
				mem.setMemNo(rset.getInt("MEM_NO"));
				mem.setMemId(rset.getString("MEM_ID"));
				mem.setMemName(rset.getString("MEM_NAME"));
				mem.setAddress(rset.getString("ADDRESS"));
				mem.setAddressDetail(rset.getString("ADDRESS_DETAIL"));
				mem.setPhone(rset.getString("PHONE"));
				mem.setEmail(rset.getString("EMAIL"));
				mem.setBirthDate(rset.getString("BIRTH_DATE"));
				mem.setGender(rset.getString("GENDER"));
				mem.setGrade(rset.getString("GRADE"));
				mem.setEnrollDate(rset.getDate("ENROLL_DATE"));
				mem.setStopYn(rset.getString("STOP_YN"));
				mem.setStopDate(rset.getDate("STOP_DATE"));
				mem.setDropYn(rset.getString("DROP_YN"));
				mem.setDropDate(rset.getDate("DROP_DATE"));
				mem.setRepCount(rset.getInt	("REP_COUNT"));
				
				memberList.add(mem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberList;
	}
	
	public int selectSearchListCount(Connection conn, String keyword, String condition) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(*) FROM(SELECT MEM_NO FROM MEMBER WHERE" + keyword+ "LIKE '%" + keyword + "%')";
		
		try {
			pstmt = conn.prepareStatement(sql);
			listCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Member> selectMemListByKeyword(Connection conn, String keyword, String condition){
		ArrayList<Member> memberList = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT MEM_NO, MEM_ID, MEM_NAME, ADDRESS, ADDRESS_DETAIL, PHONE, EMAIL, BIRTH_DATE, GENDER, GRADE, ENROLL_DATE, "
				 + "STOP_YN, STOP_DATE, DROP_YN, DROP_DATE, REP_COUNT FROM MEMBER WHERE " + condition + " LIKE '%" + keyword + "%' "
				 + "ORDER BY MEM_NO DESC";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member mem = new Member();
				
				mem.setMemNo(rset.getInt("MEM_NO"));
				mem.setMemId(rset.getString("MEM_ID"));
				mem.setMemName(rset.getString("MEM_NAME"));
				mem.setAddress(rset.getString("ADDRESS"));
				mem.setAddressDetail(rset.getString("ADDRESS_DETAIL"));
				mem.setPhone(rset.getString("PHONE"));
				mem.setEmail(rset.getString("EMAIL"));
				mem.setBirthDate(rset.getString("BIRTH_DATE"));
				mem.setGender(rset.getString("GENDER"));
				mem.setGrade(rset.getString("GRADE"));
				mem.setEnrollDate(rset.getDate("ENROLL_DATE"));
				mem.setStopYn(rset.getString("STOP_YN"));
				mem.setStopDate(rset.getDate("STOP_DATE"));
				mem.setDropYn(rset.getString("DROP_YN"));
				mem.setDropDate(rset.getDate("DROP_DATE"));
				mem.setRepCount(rset.getInt	("REP_COUNT"));
				
				memberList.add(mem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return memberList;
	}
	
	public int deleteQnaAnswer(Connection conn, int qnaNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteQnaAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Member selectMember(Connection conn, int memNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Member mem = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mem = new Member();
				
				mem.setMemNo(rset.getInt("MEM_NO"));
				mem.setMemId(rset.getString("MEM_ID"));
				mem.setMemPwd(rset.getString("MEM_PWD"));
				mem.setMemName(rset.getString("MEM_NAME"));
				mem.setAddress(rset.getString("ADDRESS"));
				mem.setAddressDetail(rset.getString("ADDRESS_DETAIL"));
				mem.setPhone(rset.getString("PHONE"));
				mem.setEmail(rset.getString("EMAIL"));
				mem.setBirthDate(rset.getString("BIRTH_DATE"));
				mem.setGender(rset.getString("GENDER"));
				mem.setGrade(rset.getString("GRADE"));
				mem.setEnrollDate(rset.getDate("ENROLL_DATE"));
				mem.setDropYn(rset.getString("DROP_YN"));
				mem.setDropDate(rset.getDate("DROP_DATE"));
				mem.setRepCount(rset.getInt("REP_COUNT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mem;
	}
	
	public int updateMemberInfo(Connection conn, Member mem) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMemberInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getBirthDate());
			pstmt.setString(2, mem.getGrade());
			pstmt.setString(3, mem.getPhone());
			pstmt.setString(4, mem.getEmail());
			pstmt.setString(5, mem.getAddress());
			pstmt.setString(6, mem.getAddressDetail());
			pstmt.setInt(7, mem.getMemNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int resetMemPwd(Connection conn, int memNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("resetMemPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int selectQnaListCountByQnaCheck(Connection conn, String qnaCheck) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQnaListCountByQnaCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qnaCheck);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public int selectReportCountList(Connection conn, String table, String repYn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT COUNT(*) FROM REPORT WHERE REP_YN LIKE '" + repYn + "' AND " + table + " LIKE '%'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
}
