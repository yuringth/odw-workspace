package com.odw.member.model.service;

import static com.odw.common.JDBCTemplate.*;

import java.sql.Connection;

import com.odw.member.model.dao.MemberDao;
import com.odw.member.model.vo.Member;

public class MemberService {

	
	//로그인 
	
	public Member loginMember(String memId, String memPwd) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().loginMember(conn, memId, memPwd);
		
		close(conn);
		
		return m;
	}
	
	
	
	//아이디중복체크
	
	public int idCheck(String checkId) {
		
		Connection conn = getConnection();
		
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		
		return count;
		
	}
	
	
	//아이디찾기
	
	public String findId(String memName, String email) {
		
		Connection conn = getConnection();
		
		String originId = new MemberDao().findId(conn, memName, email);
		
		close(conn);
		
		return originId;
		
		
	}
	
	
	
	
	// 비밀번호 찾기
	
	public String findPwd(Member m) {
		
		Connection conn = getConnection();
		
		String memPwd = new MemberDao().findPwd(conn, m);
		
		close(conn);
		
		return memPwd;
		
	}
	
	
	
	
	//회원가입

	public int insertMember(Member member) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn, member);		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	//충원님
	
	
	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		Member updateMem = null;
		
		
		if(result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, m);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
	
	}
	
	
	
	public Member updatePwd(String memId, String memPwd, String updatePwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn, memId, memPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMemberId(conn, memId);
			
		}else {
			rollback(conn);
		}
		close(conn);
		
		return updateMem;
	}
	
	
	
	
	
	public Member deleteMem(String memId, String memPwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMem(conn, memId, memPwd);
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMemberId(conn, memId);
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
		
	}
	
		
		
		
		
	
	
}
