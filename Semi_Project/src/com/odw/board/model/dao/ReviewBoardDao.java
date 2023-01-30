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
import com.odw.common.model.vo.PageInfo;
import com.odw.reply.model.vo.Reply;

public class ReviewBoardDao {
	private Properties prop = new Properties();

	public ReviewBoardDao() {
		String fileName = ReviewBoardDao.class.getResource("/sql/board/review/reviewBoard-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// -1
	public int selectReviewBoardListCount(Connection conn) {
		
	// SELECT => ResultSet 근데 반환형은 int???
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset =null;
			
			String sql = prop.getProperty("selectReviewBoardListCount");
			
			
			try {
				pstmt=conn.prepareStatement(sql);
				rset =pstmt.executeQuery();
				
				if(rset.next()) {
					listCount = rset.getInt("COUNT(*)");
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return listCount;
		}	
	// 모르겠음
	public ArrayList<Board> selectReviewBoardList(Connection conn, PageInfo pi){
		
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewBoardList");
		
		
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
				int endRow = startRow + pi.getBoardLimit() -1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2,  endRow);
				
				rset= pstmt.executeQuery();
				
				while(rset.next()) {
					
					Board b= new Board();
					b.setBoardNo(rset.getInt("BOARD_NO"));
					b.setFileNo(rset.getInt("FILE_NO"));
					b.setBoardCategory(rset.getString("BOARD_CATEGORY"));
					b.setBoardTitle(rset.getString("BOARD_TITLE"));
					b.setBoardContent(rset.getString("BOARD_CONTENT"));
					b.setMemId(rset.getString("MEM_ID"));
					b.setBoardCount(rset.getInt("BOARD_COUNT"));
					b.setBoardAnswerYN(rset.getString("BOARD_ANSWER_YN"));
					b.setCreateDate(rset.getDate("CREATE_DATE"));
					b.setTitleImg(rset.getString("TITLEIMG"));
					
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
	// 0
	public int insertReviewBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReviewBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
//			pstmt.setString(3, b.getBoardCategory());
			pstmt.setInt(3, b.getMemNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	// 1
	public int insertReviewAttachment(Connection conn, Attachment reAt) {
		int result = 1;
		PreparedStatement pstmt = null;
		
		System.out.println(reAt.getOriginName());
		System.out.println(reAt.getChangeName());
		System.out.println(reAt.getFilePath());
		
		String sql = prop.getProperty("insertReviewAttachment");
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, reAt.getOriginName());
			pstmt.setString(2, reAt.getChangeName());
			pstmt.setString(3, reAt.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	// 리뷰 시작
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
	
	public Board selectReviewBoardDetail(Connection conn, int boardNo) {
		
		Board b = null;
		PreparedStatement pstmt = null; 
		ResultSet rset =null;
		
		String sql = prop.getProperty("selectReviewBoardDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setGrade(rset.getString("GRADE"));
				b.setGender(rset.getString("GENDER"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		return b;
	}
	
	public Attachment selectReviewBoardDetailAttachment(Connection conn, int boardNo) {
		
		Attachment reAt = new Attachment();
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		
		String sql = prop.getProperty("selectReviewBoardDetailAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				reAt.setFileNo(rset.getInt("FILE_NO"));
				reAt.setOriginName(rset.getString("ORIGIN_NAME"));
				reAt.setFilePath(rset.getString("TITLEIMG"));
				reAt.setChangeName(rset.getString("CHANGE_NAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return reAt;
		
	}
	
	public ArrayList<Reply> selectReviewBoardReplyList(Connection conn, int boardNo) {
	
		ArrayList<Reply> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewBoardReplyList");
		

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setMemId(rset.getString("MEM_ID"));
				r.setCreateDate(rset.getString("REPLY_CREATE_DATE"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		
		return list;

	}


	public int insertReplyBoardReply(Connection conn, Reply r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReplyBoardReply");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,r.getReplyContent());
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
	
	public int updateReviewBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReviewBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2,  b.getBoardContent());
			pstmt.setInt(3,  b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}
	
	public int updateReviewBoardAttachment(Connection conn, Attachment reAt) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReviewBoardAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reAt.getFilePath());
			pstmt.setString(2, reAt.getOriginName());
			pstmt.setString(3, reAt.getChangeName());
			pstmt.setInt(4, reAt.getFileNo());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int deleteReviewBoard(Connection conn, int boardNo ) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReviewBoard");
		
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
	
	
	
	
	
	
	
	
	
	
public ArrayList<Board> selectReviewBoardPreview(Connection conn){
		
		
		ArrayList<Board> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewBoardPreview");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board();

				
			    b.setTitleImg(rset.getString("TITLEIMG"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				
			
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
