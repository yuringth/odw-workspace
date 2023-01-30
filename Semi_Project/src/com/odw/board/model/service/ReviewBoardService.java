package com.odw.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.odw.common.JDBCTemplate.*;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.dao.ReviewBoardDao;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;
import com.odw.reply.model.vo.Reply;

public class ReviewBoardService {

	public int selectReviewBoardListCount() {
			
			Connection conn = getConnection();
			
			int listCount = new ReviewBoardDao().selectReviewBoardListCount(conn);

			return listCount;
				
	}
	

	public ArrayList<Board> selectReviewBoardList(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new ReviewBoardDao().selectReviewBoardList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public int insertReviewBoard(Board b, Attachment reAt) {
		
		Connection conn = getConnection();
		
		// board테이블에 insert
		int result1 = new ReviewBoardDao().insertReviewBoard(conn, b);
		
		int result2 = new ReviewBoardDao().insertReviewAttachment(conn, reAt);
		
		
		if((result1 * result2) > 0) {
			
			commit(conn);
	
		}else {
		
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
	}
	
	
	
	//여기 사진있는 게시판 카운트올리고 조회 (상세)
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		int result = new ReviewBoardDao().increaseCount(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	// 여기 보드조회하면서 사진도 조회(상세)
	public Board selectReviewBoardDetail(int boardNo) {
		
		Connection conn = getConnection();
		
		Board b = new ReviewBoardDao().selectReviewBoardDetail(conn, boardNo);
		
		close(conn);
		
		return b;

	}
	
	public Attachment selectReviewBoardDetailAttachment(int boardNo) {
		
		Connection conn = getConnection();
		
		Attachment reAt = new ReviewBoardDao().selectReviewBoardDetailAttachment(conn, boardNo);
		
		close(conn);
		
		return reAt;
	}
	
	public ArrayList<Reply> selectReviewBoardReplyList(int boardNo){
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new ReviewBoardDao().selectReviewBoardReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	
	}


	public int insertReplyBoardReply(Reply r) {
		Connection conn = getConnection();
		
		int result = new ReviewBoardDao().insertReplyBoardReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateReviewBoard(Board b, Attachment reAt) {
		
		Connection conn = getConnection();
		
		int result1 = new ReviewBoardDao().updateReviewBoard(conn, b);
		int result2 = new ReviewBoardDao().updateReviewBoardAttachment(conn, reAt);
	
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return (result1 * result2);
	}
	
public int deleteReviewBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new ReviewBoardDao().deleteReviewBoard(conn, boardNo );
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		
		return result;
	}







//승혜

		public ArrayList<Board> selectReviewBoardPreview(){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new ReviewBoardDao().selectReviewBoardPreview(conn);
		
		close(conn);
		
		return list;
		
	}
	
	
	
	
}
