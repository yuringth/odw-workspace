package com.odw.board.model.service;

import static com.odw.common.JDBCTemplate.close;
import static com.odw.common.JDBCTemplate.commit;
import static com.odw.common.JDBCTemplate.getConnection;
import static com.odw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.dao.FeedDao;
import com.odw.board.model.vo.Board;
import com.odw.reply.model.vo.Reply;

public class FeedService {


	public int insertFeedBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new FeedDao().insertFeedBoard(conn, b);
		
		int result2 = new FeedDao().insertFeedAttachment(conn, at);
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
		
	}
	

	
	
	public ArrayList<Board> selectFeedBoard() {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new FeedDao().selectFeedBoard(conn);
		
		close(conn);
		
		return list;
	}
	
	
	
	
	
	public Board detailFeedBoard(int feedBoardNo) {
		
		Connection conn = getConnection();
		
		Board b = new FeedDao().detailFeedBoard(conn, feedBoardNo);
		
		close(conn);
		
		return b;
		
	}
	
	
	public Attachment detailFeedAttachment(int feedBoardNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new FeedDao().detailFeedAttachment(conn, feedBoardNo);
		
		close(conn);
		
		return at;
		
	}
	
	
	// 3) 댓글삭제하려고 만들었는데 오류뜨면 삭제하기
	public ArrayList<Reply> detailFeedReply(int feedBoardNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new FeedDao().detailFeedReply(conn, feedBoardNo);
		
		close(conn);
		
		return list;
		
	}
	
	
	
	
	
	public int deleteFeedBoard(int feedBoardNo) {
		
		Connection conn = getConnection();
		
		int result = new FeedDao().deleteFeedBoard(conn, feedBoardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	
	public Board selectFeedFormBoard(int feedBoardNo) {
		
		Connection conn = getConnection();
		
		Board b = new FeedDao().selectFeedFormBoard(conn, feedBoardNo);
		
		close(conn);
		
		return b;
		
	}
	
	
	public Attachment selectFeedAttachment(int feedBoardNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new FeedDao().selectFeedAttachment(conn, feedBoardNo);
		
		close(conn);
		
		return at;
		
		
	}
	
	
	
	public int updateFeedBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new FeedDao().updateFeedBoard(conn, b);
		
		int result2 = new FeedDao().updateFeedAttachment(conn, at);
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2);
		
	}



	// 좋아요기능
	public Board insertLike(int boardNo, int memNo) {
		
		Connection conn = getConnection();
		
		int result1 = new FeedDao().insertLike(conn, boardNo, memNo);
		int result2 = new FeedDao().UpdateFeedBoardLikeCount(conn, boardNo);
		Board b = new Board();
		
		if((result1 * result2) > 0) {
			commit(conn);
			b = new FeedDao().detailFeedBoard(conn, boardNo);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
		
	}



	// 좋아요 기능
	public int selectFeedLikeBtn(int boardNo, int memNo) {
		
		Connection conn = getConnection();
		
		int result = new FeedDao().selectFeedLikeBtn(conn, boardNo, memNo);
		
		close(conn);
		
		return result;
	}




	public Board downLike(int boardNo, int memNo) {
		Connection conn = getConnection();
		
		int result1 = new FeedDao().deleteLike(conn, boardNo, memNo);
		int result2 = new FeedDao().downFeedBoardLikeCount(conn, boardNo);
		Board b = new Board();
		
		if((result1 * result2) > 0) {
			commit(conn);
			b = new FeedDao().detailFeedBoard(conn, boardNo);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return b;
	}
	
	
	
	
	// 댓글 목록 조회 기능
	public ArrayList<Reply> selectFeedReply(int boardNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new FeedDao().selectFeedReply(conn, boardNo);
		
		close(conn);
		
		return list;
		
	}
	
	
	// 댓글 작성
	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new FeedDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
		
	}
	
	
	// 댓글 삭제
	public int deleteReply(int replyNo) {
		
		Connection conn = getConnection();
		
		int result = new FeedDao().deleteReply(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}



//좋아요 테이블에서 삭제
	public int deleteLike(int boardNo, int memNo) {
		
		Connection conn = getConnection();
		
		int result = new FeedDao().deleteLike(conn, memNo, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}



	// 메인화면에 피드게시판 글 목록 불러오기
	public ArrayList<Board> selectFeedPreview() {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new FeedDao().selectFeedPreview(conn);
		
		close(conn);
		
		return list;	
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
