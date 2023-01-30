package com.odw.board.model.service;

import static com.odw.common.JDBCTemplate.close;
import static com.odw.common.JDBCTemplate.commit;
import static com.odw.common.JDBCTemplate.getConnection;
import static com.odw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.dao.BoardDao;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;
import com.odw.board.model.vo.Qna;

public class BoardService {
	
	public int selectListCount(String memId) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn, memId);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectMyBoard(PageInfo pi, String memId) {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectMyBoard(conn, pi, memId);
		
		close(conn);
		
		return list;
		
	}
	public int increaseCount(int bno) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, bno);
		
		if(result >= 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public Board selectBoard(int bno, int memNo) {
		
		Connection conn = getConnection();
		
		Board b = new BoardDao().selectBoard(conn, bno, memNo);
		
		close(conn);
		
		return b;
		
	}
	public Qna selectQna(int memNo) {
		
		Connection conn = getConnection();
		
		Qna q = new BoardDao().selectQna(conn, memNo);
		
		close(conn);
		
		return q;
		
	}
	
	public Attachment selectAttachment(int bno) {
		
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, bno);
		
		close(conn);
		
		return at;
	}
	public int selectReplyListCount(int memNo) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectReplyListCount(conn, memNo);
		
		close(conn);
	
		return listCount;
	}
	public ArrayList<Board> selectMyReply(PageInfo pi, int memNo){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectMyReply(conn, pi, memNo);
		
		close(conn);
		
		return list;
	}
	public int myLikeListCount(String memId) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().myLikeListCount(conn, memId);
		
		
		close(conn);
		return listCount;
	}
	public ArrayList<Board> myLikeList(PageInfo pi, String memId){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().myLikeList(conn, pi, memId);
		close(conn);
		return list;
	}
	public int myQuestionCount(String memId) {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().myQuestionCount(conn, memId);
		
		close(conn);
		
		return listCount;
	}
	public ArrayList<Qna> myQuestionList(PageInfo pi, String memId){
		
		Connection conn = getConnection();
		
		ArrayList<Qna> list = new BoardDao().myQuestionList(conn, pi, memId);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Board> myWithList(int memNo){

		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().myWithList(conn, memNo);
		close(conn);
		return list;
	}
	public ArrayList<Board> myRecruitList(int memNo){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().myRecruitList(conn, memNo);
		close(conn);
		return list;
	}
	
}
