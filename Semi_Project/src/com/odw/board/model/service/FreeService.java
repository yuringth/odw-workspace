package com.odw.board.model.service;

import static com.odw.common.JDBCTemplate.close;
import static com.odw.common.JDBCTemplate.commit;
import static com.odw.common.JDBCTemplate.getConnection;
import static com.odw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.dao.FreeDao;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;
import com.odw.reply.model.vo.Reply;


public class FreeService {

	
	public int selectListCount() {
		// * listCount : 총 게시글의 수 
		
		Connection conn = getConnection();
		
		int listCount = new FreeDao().selectListCount(conn);
		// SELECT문의 결과는 ResultSet. 그치만
		// 게시글의 총 개수를 구하고 있으니, 정수형으로 돌아와야함
		close(conn);
		
		return listCount;
	}
	
	
	public ArrayList<Board> selectFreeList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new FreeDao().selectFreeList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	
	public int insertFreeBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		// 하나의 메소드에 하나의 sql문을 사용할 수 있음
		// 1) board 테이블에 insert
		int result1 = new FreeDao().insertFreeBoard(conn, b);
		
		// 2) attachment 테이블에 insert 
		int result2 = 1; // 0으로 초기화를 하면 board insert가 되면 1이 되기때문에. 또한 result1(1)*result2(0)=0이 된다
		if(at != null) {
			result2 = new FreeDao().insertFreeAttachment(conn, at);
		}
		
		// 3) 트랜잭션 처리
		// result1도 성공이고 result2도 성공이면 commit
		// 둘중 하나라도 실패하면 => rollback
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2);
		
		
	}
	
	public Board detailFreeBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		Board b = new FreeDao().detailFreeBoard(conn, boardNo);
		
		close(conn);
		
		return b;
		
	}
	
	public Attachment detailAttachmentBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new FreeDao().detailAttachmentBoard(conn, boardNo);
		
		close(conn);
		
		return at;
	}
	
	public int increaseCount(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new FreeDao().increaseCount(conn, boardNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	
	public Board selectFreeBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		Board b = new FreeDao().selectFreeBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}
	
	public Attachment selectFreeAttachment(int boardNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new FreeDao().selectFreeAttachment(conn, boardNo);
		
		close(conn);
		
		return at;
	}
	
	
	public int updateFreeBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		// 새로운 첨부파일 없고, 게시글만 수정할 경우 => update board
		int result1 = new FreeDao().updateFreeBoard(conn, b);
		
		
		// 새로운 첨부파일이 있는경우
		int result2 = 1;
		if(at != null) {
			if(at.getFileNo() != 0) {
				// 새로운 첨부파일 있고, 기존에 첨부파일 있을 경우 => update board, update attachment
				result2 = new FreeDao().updateFreeAttachment(conn, at);
			} else {
				// 새로운 첨부파일 있고, 기존에 첨부파일 없을경우 => update board, insert attachment
				result2 = new FreeDao().insertFreeNewAttachment(conn, at);
			}
		}
		
		// 트랜잭션처리
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2);
	}
	
	
	public int deleteFreeBoard(int board) {
		
		Connection conn = getConnection();
		
		int result = new FreeDao().deleteFreeBoard(conn, board);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		 
		return result;
		
	}
	
	
	
	
	// 댓글기능
	public ArrayList<Reply> selectFreeReplyList(int boardNo){

		Connection conn = getConnection();
		
		ArrayList<Reply> list = new FreeDao().selectFreeReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}
	
	
	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new FreeDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	public int deleteReply(int replyNo) {

		Connection conn = getConnection();
		
		int result = new FreeDao().deleteReply(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
}
