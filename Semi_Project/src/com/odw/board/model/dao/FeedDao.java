package com.odw.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.odw.common.JDBCTemplate.*;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.vo.Board;
import com.odw.reply.model.vo.Reply;

import oracle.net.aso.r;

public class FeedDao {
	
	private Properties prop = new Properties();
	
	public FeedDao() {
		
		String fileName = FeedDao.class.getResource("/sql/board/feedBoard-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertFeedBoard(Connection conn, Board b) {
		// insert -> 1행
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertFeedBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, b.getMemNo());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getKeyword());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int insertFeedAttachment(Connection conn, Attachment at) {
		int result = 1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertFeedAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public ArrayList<Board> selectFeedBoard(Connection conn) {
		// select => ResultSet => ArrayList
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFeedBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setFileNo(rset.getInt("FILE_NO"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setLikeCount(rset.getInt("LIKE_COUNT"));
				b.setTitleImg(rset.getString("TITLEIMG"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				
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
	
	
	
	public Board detailFeedBoard(Connection conn, int feedBoardNo) {
		// select => resultset => 1 pk
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("detailFeedBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, feedBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO")); 
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setKeyword(rset.getString("KEYWORD"));
				//좋아요기능때문에 추가함
				b.setLikeCount(rset.getInt("LIKE_COUNT"));
				//댓글 추가기능 때문에 추가함
				b.setMemNo(rset.getInt("MEM_NO"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	
	public Attachment detailFeedAttachment(Connection conn, int feedBoardNo) {
		// select => fileNo pk키
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("detailFeedAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, feedBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				at = new Attachment();
				
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setFilePath(rset.getString("TITLEIMG"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}
	
	
	// 3) 댓글삭제하려고 만들었는데 오류뜨면 삭제하기
	public ArrayList<Reply> detailFeedReply(Connection conn, int feedBoardNo) {
		// 보드넘버에 관하여,,,댓글 식별자를 뿌려주고싶지만,, 댓글은 몇개가 있는지모르니...arrayList?
		ArrayList<Reply> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("detailFeedReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, feedBoardNo);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		//System.out.println(list);
		return list;
	}
	
	
	
	
	
	
	public int deleteFeedBoard(Connection conn, int boardFeedNo) {
		// update => int형
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteFeedBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardFeedNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public Board selectFeedFormBoard(Connection conn, int feedBoardNo) {
		// select
		
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFeedFormBoard");
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, feedBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setKeyword(rset.getString("KEYWORD"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
		
	}
	
	
	public Attachment selectFeedAttachment(Connection conn, int feedBoardNo) {
		//select
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFeedAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, feedBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				at = new Attachment();
				
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
		
		
	}
	
	
	
	
	public int updateFeedBoard(Connection conn, Board b) {
		// update => pk 1행
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFeedBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardContent());
			pstmt.setString(2, b.getKeyword());
			pstmt.setInt(3, b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

	public int updateFeedAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFeedAttachment");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getFilePath());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}


	// 좋아요기능
	public int insertLike(Connection conn, int boardNo, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLike");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	//좋아요기능
	public int UpdateFeedBoardLikeCount(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("UpdateFeedBoardLikeCount");

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

	// 좋아요기능
	public int selectFeedLikeBtn(Connection conn, int boardNo, int memNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		Board b = new Board();
		String sql = prop.getProperty("selectFeedLikeBtn");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, boardNo);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				b.setResult(rset.getInt("COUNT"));
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}


	public int deleteLike(Connection conn, int boardNo, int memNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteLike");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int downFeedBoardLikeCount(Connection conn, int boardNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("downFeedBoardLikeCount");

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


	
	// 댓글 목록 조회 기능
	public ArrayList<Reply>  selectFeedReply(Connection conn, int boardNo) {
		// select => ArrayList<Reply>
		ArrayList<Reply> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFeedReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setReplyNo(rset.getInt("REPLY_NO")); // 식별하기 위해서
				r.setMemId(rset.getString("MEM_ID"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setCreateDate(rset.getString("CREATE_DATE"));

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
	
	

	public int insertReply(Connection conn, Reply r) {
		// insert문 => 1행이 추가
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReply");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, r.getMemNo());
			pstmt.setInt(2, r.getBoardNo());
			pstmt.setString(3, r.getReplyContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
		
	}


	// 댓글삭제
	public int deleteReply(Connection conn, int replyNo) {
		// 댓글번호 식별자 이용하여 update => 1행
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}


	// 메인화면에 피드게시판 글 목록 불러오기
	public ArrayList<Board> selectFeedPreview(Connection conn) {
		
		ArrayList<Board> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFeedPreview");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board();

				
			    b.setTitleImg(rset.getString("TITLEIMG"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardCount(rset.getInt("LIKE_COUNT"));
				
			
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


}
	
 
	
	
	
	
	
	
	

