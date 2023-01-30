package com.odw.board.model.dao;

import static com.odw.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.board.model.vo.Board;
import com.odw.reply.model.vo.Reply;

public class QNABoardDao {

	private Properties prop = new Properties();

	public QNABoardDao() {
		String fileName = QNABoardDao.class.getResource("/sql/board/qna/qnaBoard-mapper.xml").getPath();
		
			try {
				prop.loadFromXML(new FileInputStream(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	
	
	public ArrayList<Board> selectQNAList(Connection conn){
		
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQNAList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardAnswerYN(rset.getString("BOARD_ANSWER_YN"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setGrade(rset.getString("GRADE"));
				b.setBoardNo(rset.getInt("BOARD_NO"));
				
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertQNABoard(Connection conn, Board b) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertQNABoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getMemNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public Board selectQNABoard(Connection conn, int boardNo) {
		Board b = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		String sql = prop.getProperty("selectQNABoard");
		

		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setBoardAnswerYN(rset.getString("BOARD_ANSWER_YN"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setGrade(rset.getString("GRADE"));
				b.setGender(rset.getString("GENDER"));
				b.setBoardNo(rset.getInt("BOARD_NO"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return b;
	}
	
	public int updateQNABoard(Connection conn,  Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateQNABoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteQNABoard(Connection conn, int boardNo ) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteQNAboard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> qnaAnswerNSelect(Connection conn) {
		
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("qnaAnswerNSelect");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardAnswerYN(rset.getString("BOARD_ANSWER_YN"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setGrade(rset.getString("GRADE"));
				b.setBoardNo(rset.getInt("BOARD_NO"));
				// 추가적으로 QnaAnswer_YN 속성이 'N'인 놈들만 받아줘야함
				
				
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	public ArrayList<Reply> selectQNABoardReplyList(Connection conn, int boardNo ){
		
		ArrayList<Reply> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQNABoardReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				Reply r = new Reply();
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setMemId(rset.getString("MEM_ID"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setCreateDate(rset.getString("REPLY_CREATE_DATE"));
				list.add(r);
			}	

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertQNABoardReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQNABoardReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getBoardNo());
			pstmt.setInt(3, r.getMemNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Reply> countReplyList(Connection conn){
		
		ArrayList<Reply> rList = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("countReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setBoardNo(rset.getInt("BOARD_NO"));
				r.setReplyNo(rset.getInt("REPLY_NO"));
				
				rList.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}
	
	
	
}
