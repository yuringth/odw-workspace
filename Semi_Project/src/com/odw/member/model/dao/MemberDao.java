package com.odw.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.odw.member.model.vo.Member;
import static com.odw.common.JDBCTemplate.*;

public class MemberDao {
	
	
	private Properties prop = new Properties();

	public MemberDao() {
		
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
/*
 * 로그인 해주는 메소드	
 */
	
	public Member loginMember(Connection conn, String memId, String memPwd) {
		
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				m = new Member(rset.getInt("MEM_NO"),
							  rset.getString("MEM_ID"),
							  rset.getString("MEM_PWD"),
							  rset.getString("MEM_NAME"),
							  rset.getString("ADDRESS"),
							  rset.getString("ADDRESS_DETAIL"),
							  rset.getString("PHONE"),
							  rset.getString("EMAIL"),
							  rset.getString("BIRTH_DATE"),
							  rset.getString("GENDER"),
							  rset.getString("GRADE"),
							  rset.getDate("ENROLL_DATE"),
							  rset.getString("STOP_YN"),
							  rset.getDate("STOP_DATE"),
							  rset.getString("DROP_YN"),
							  rset.getDate("DROP_DATE"),
							  rset.getInt("REP_COUNT"));
						
				
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		
		return m;
		
		
		
	}
	
	
	
/*
 * 아이디 중복 체크 메소드	
 */
	
	
	public int idCheck(Connection conn, String checkId) {
		
		
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("idCheck");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	
	
	
	
/*
 * 아이디 찾기
 */
	
	public String findId(Connection conn, String memName, String email) {
		

		String originId = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null; 
		
		String sql = prop.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				originId = rset.getString("MEM_ID");
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return originId;
		
	}

	

	
	
/*
 * 비밀번호 찾기
 */
	
	public String findPwd(Connection conn, Member m) {
		
		String memPwd = null;
		
		PreparedStatement pstmt = null; 
		
		ResultSet rset = null; 
		
		String sql = prop.getProperty("findPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemName());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getMemId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memPwd = rset.getString("MEM_PWD");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return memPwd;
		
		
		
	}
	

	

	
	
	
	
// 회원가입
	
	public int insertMember(Connection conn, Member member) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPwd());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getAddressDetail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getBirthDate());
			pstmt.setString(9, member.getGender());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	// 충원님
	
	
	public int updateMember(Connection conn, Member m) {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemName());
			pstmt.setString(2, m.getAddress());
			pstmt.setString(3, m.getAddressDetail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getMemId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public Member selectMember(Connection conn, Member m) {
		Member updateMem = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemId());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				updateMem = new Member(rset.getInt("MEM_NO"),
									   rset.getString("MEM_ID"),
									   rset.getString("MEM_PWD"),
									   rset.getString("MEM_NAME"),
									   rset.getString("ADDRESS"),
									   rset.getString("ADDRESS_DETAIL"),
									   rset.getString("PHONE"),
									   rset.getString("EMAIL"),
									   rset.getString("BIRTH_DATE"),
									   rset.getString("GENDER"),
									   rset.getString("GRADE"),
									   rset.getDate("ENROLL_DATE"),
									   rset.getString("STOP_YN"),
									   rset.getDate("STOP_DATE"),
									   rset.getString("DROP_YN"),
									   rset.getDate("DROP_DATE"),
									   rset.getInt("REP_COUNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	public int updatePwd(Connection conn, String userId, String userPwd, String updatePwd) {
		int result = 0;
		PreparedStatement pstmt = null;
	
		String sql = prop.getProperty("updatePwd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	
	
	}
	public Member selectMemberId(Connection conn, String memId) {
		Member updateMem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				updateMem = new Member(rset.getInt("MEM_NO"),
									  rset.getString("MEM_ID"),
									  rset.getString("MEM_PWD"),
									  rset.getString("MEM_NAME"),
									  rset.getString("ADDRESS"),
									  rset.getString("ADDRESS_DETAIL"),
									  rset.getString("PHONE"),
									  rset.getString("EMAIL"),
									  rset.getString("BIRTH_DATE"),
									  rset.getString("GENDER"),
									  rset.getString("GRADE"),
									  rset.getDate("ENROLL_DATE"),
									  rset.getString("STOP_YN"),
									  rset.getDate("STOP_DATE"),
									  rset.getString("DROP_YN"),
									  rset.getDate("DROP_DATE"),
									  rset.getInt("REP_COUNT"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return updateMem;
		
	}
	public int deleteMem(Connection conn, String memId, String memPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPwd);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

	
	
	

