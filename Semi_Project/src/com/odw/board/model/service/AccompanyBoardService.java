package com.odw.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.dao.AccompanyBoardDao;
import com.odw.board.model.vo.Accept;
import com.odw.board.model.vo.Board;
import com.odw.reply.model.vo.Reply;

import static com.odw.common.JDBCTemplate.*;

public class AccompanyBoardService {

	public ArrayList<Board> selectAccompanyBoardList() {

		Connection conn = getConnection();
		
		ArrayList<Board> list = new AccompanyBoardDao().selectAccompanyBoardList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Board> selectYAccompanyBoard() {

		Connection conn = getConnection();
		
		ArrayList<Board> list = new AccompanyBoardDao().selectYAccompanyBoard(conn);
		
		close(conn);
		
		return list;
	}

	public int accompanyBoardIncreaseCount(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new AccompanyBoardDao().accompanyBoardIncreaseCount(conn, boardNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	public Board selectAccompanyBoard(int boardNo) {

		Connection conn = getConnection();
		
		Board b  = new AccompanyBoardDao().selectAccompanyBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}

	public ArrayList<Reply> selectReplyList(int boardNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new AccompanyBoardDao().selectReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}

	public int insertAccompanyBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new AccompanyBoardDao().insertAccompanyBoard(conn, b);
		
		int result2 = new AccompanyBoardDao().insertAccompanyAttachment(conn, at);
		
		if ((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
	}

	public int insertAccompanyBoardReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new AccompanyBoardDao().insertAccompanyBoardReply(conn, r);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteAccompanyBoardReply(int replyNo) {
		
		Connection conn = getConnection();
		
		int result = new AccompanyBoardDao().deleteAccompanyBoardReply(conn, replyNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int updateAccompanyBoardReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new AccompanyBoardDao().updateAccompanyBoardReply(conn, r);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Board accompanyAdd(Accept a) {
		
		Connection conn = getConnection();
		
		int result1 = new AccompanyBoardDao().accompanyAddInsertAccept(conn, a);
		
		int result2 = new AccompanyBoardDao().accompanyAddMemberNow(conn, a);
		
		Board b = new AccompanyBoardDao().accompanySelectMemberNow(conn, a);
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
		
	}

	public Board SelectAccompanyAddButton(Accept a) {
		
		Connection conn = getConnection();
		
		int result = new AccompanyBoardDao().SelectAccompanyAddButton(conn, a);
		
		Board b = new AccompanyBoardDao().accompanySelectMemberNow(conn, a);
		
		b.setResult(result);
		
		close(conn);
		
		return b;
		
	}

	public Board accompanyCancel(Accept a) {

		Connection conn = getConnection();
		
		int result1 = new AccompanyBoardDao().accompanyAddDeleteAccept(conn, a);
		
		int result2 = new AccompanyBoardDao().accompanyUpdateMemberNow(conn, a);
		
		Board b = new AccompanyBoardDao().accompanySelectMemberNow(conn, a);
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
	}

	public ArrayList<Accept> selectAccompanyBoardAccept(int boardNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Accept> acceptList  = new AccompanyBoardDao().selectAccompanyBoardAccept(conn, boardNo);
		
		close(conn);
		
		return acceptList;
	}


	
	
	
	
	
	
// 승혜
	
	
	public ArrayList<Board> selectAccompanyBoardPreview(){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new AccompanyBoardDao().selectAccompanyBoardPreview(conn);
		
		close(conn);
		
		return list;
		
	}
	
}
