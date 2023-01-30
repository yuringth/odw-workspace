package com.odw.board.model.service;

import static com.odw.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.board.model.dao.QNABoardDao;
import com.odw.board.model.vo.Board;
import com.odw.reply.model.vo.Reply;

public class QNABoardService {
	
	public ArrayList<Board> selectQNAList(){
	
	Connection conn = getConnection();
	
	ArrayList<Board> list = new QNABoardDao().selectQNAList(conn);
	
	close(conn);
	
	return list;
	}
	public int insertQNABoard(Board b) {
		Connection conn = getConnection();
		int result = new QNABoardDao().insertQNABoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		int result = new QNABoardDao().increaseCount(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public Board selectQNABoard(int boardNo) {
		
		Connection conn = getConnection();
		
		Board b = new QNABoardDao().selectQNABoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}
	
	public int updateQNABoard(Board b) {
		Connection conn = getConnection();
		
		int result = new QNABoardDao().updateQNABoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int deleteQNABoard(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new QNABoardDao().deleteQNABoard(conn, boardNo );
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		
		return result;
	}
	public ArrayList<Board> qnaAnswerNSelect() {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new QNABoardDao().qnaAnswerNSelect(conn);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Reply> selectQNABoardReplyList(int boardNo){
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new QNABoardDao().selectQNABoardReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}
	
	public int insertQNABoardReply(Reply r) {
		
		Connection conn = getConnection();
		int result = new QNABoardDao().insertQNABoardReply(conn, r);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public ArrayList<Reply> countReplyList(){
		Connection conn = getConnection();
		
		ArrayList<Reply> rList = new QNABoardDao().countReplyList(conn);
		
		close(conn);
		
		return rList;
	}
}
