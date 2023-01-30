package com.odw.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.vo.Accept;
import com.odw.board.model.vo.Board;
import static com.odw.common.JDBCTemplate.*;
import com.odw.information.model.dao.InformationDao;
import com.odw.reply.model.vo.Reply;

public class AccompanyBoardDao {

private Properties prop = new Properties();
	
	public AccompanyBoardDao() {
		String fileName = InformationDao.class.getResource("/sql/board/accompanyBoard-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public ArrayList<Board> selectAccompanyBoardList(Connection conn) {
		
		ArrayList<Board> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAccompanyBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardName(rset.getString("BOARD_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setBoardThumbnail(rset.getString("THUMBNAIL"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setMemberNow(rset.getInt("MEMBER_NOW"));
				b.setMemberCount(rset.getInt("MEMBER_COUNT"));
				b.setGrade(rset.getString("GRADE"));
				b.setDpDate(rset.getString("DP_DATE"));
				b.setKeyword(rset.getString("KEYWORD"));
				
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




	public ArrayList<Board> selectYAccompanyBoard(Connection conn) {
		
		ArrayList<Board> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectYAccompanyBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardName(rset.getString("BOARD_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setBoardThumbnail(rset.getString("THUMBNAIL"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setMemberNow(rset.getInt("MEMBER_NOW"));
				b.setMemberCount(rset.getInt("MEMBER_COUNT"));
				b.setGrade(rset.getString("GRADE"));
				b.setDpDate(rset.getString("DP_DATE"));
				b.setKeyword(rset.getString("KEYWORD"));
				
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




	public int accompanyBoardIncreaseCount(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("accompanyBoardIncreaseCount");
		
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




	public Board selectAccompanyBoard(Connection conn, int boardNo) {
		
		Board b = new Board();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAccompanyBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setMemNo(rset.getInt("MEM_NO"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setBoardName(rset.getString("BOARD_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setBoardThumbnail(rset.getString("THUMBNAIL"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setMemberNow(rset.getInt("MEMBER_NOW"));
				b.setGender(rset.getString("GENDER"));
				b.setMemberCount(rset.getInt("MEMBER_COUNT"));
				b.setGrade(rset.getString("GRADE"));
				b.setDpDate(rset.getString("DP_DATE"));
				b.setKeyword(rset.getString("KEYWORD"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setBirthDate(rset.getString("BIRTH_DATE"));
							
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return b;
	}




	public ArrayList<Reply> selectReplyList(Connection conn, int boardNo) {
		
		ArrayList<Reply> list = new ArrayList();
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reply r = new Reply();
				
				r.setMemId(rset.getString("MEM_ID"));
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setCreateDate(rset.getString("REPLY_CREATE_DATE"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setGrade(rset.getString("GRADE"));
				
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




	public int insertAccompanyBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAccompanyBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getMemberCount());
			pstmt.setString(2, b.getDpDate());
			pstmt.setString(3, b.getBoardTitle());
			pstmt.setString(4, b.getBoardContent());
			pstmt.setString(5, b.getKeyword());
			pstmt.setInt(6, b.getMemNo());
			pstmt.setString(7, b.getBoardName());
			
			result = pstmt.executeUpdate();
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}




	public int insertAccompanyAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAccompanyAttachment");
		
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




	public int insertAccompanyBoardReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAccompanyBoardReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getBoardNo());
			pstmt.setInt(2, r.getMemNo());
			pstmt.setString(3, r.getReplyContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public int deleteAccompanyBoardReply(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAccompanyBoardReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public int updateAccompanyBoardReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAccompanyBoardReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getReplyNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public int accompanyAddMemberNow(Connection conn, Accept a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("accompanyAddMemberNow");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, a.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public int accompanyAddInsertAccept(Connection conn, Accept a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("accompanyAddInsertAccept");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, a.getMemNo());
			pstmt.setInt(2, a.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public Board accompanySelectMemberNow(Connection conn, Accept a) {
		
		Board b = new Board();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAccompanyBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getBoardNo());
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				
				b.setMemberNow(rset.getInt("MEMBER_NOW"));
				b.setMemberCount(rset.getInt("MEMBER_COUNT"));
				b.setDpDate(rset.getString("DP_DATE"));
							
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}




	public int SelectAccompanyAddButton(Connection conn, Accept a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("SelectAccompanyAddButton");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a.getMemNo());
			pstmt.setInt(2, a.getBoardNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT(*)");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
		
	}




	public int accompanyAddDeleteAccept(Connection conn, Accept a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("accompanyAddDeleteAccept");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, a.getMemNo());
			pstmt.setInt(2, a.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}




	public int accompanyUpdateMemberNow(Connection conn, Accept a) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("accompanyUpdateMemberNow");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, a.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	public ArrayList<Accept> selectAccompanyBoardAccept(Connection conn, int boardNo) {
		
		ArrayList<Accept> acceptList = new ArrayList();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAccompanyBoardAccept");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Accept a = new Accept();
				a.setMemNo(rset.getInt("MEM_NO"));
				
				acceptList.add(a);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return acceptList;

		
	}

	
	
	
	
	
	
//승혜 동행게시판 실시간 정보 조회

	
	public ArrayList<Board> selectAccompanyBoardPreview(Connection conn){
		
		
		ArrayList<Board> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAccompanyBoardPreview");
		
		
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
