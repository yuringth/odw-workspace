package com.odw.board.model.dao;

import static com.odw.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;
import com.odw.reply.model.vo.Reply;

public class FreeDao {

	private Properties prop = new Properties();
	
	public FreeDao() {
		String fileName = FreeDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int selectListCount(Connection conn) {
		
		// SELECT => ResultSet => ArrayList()사용해야하지만, 총개시글의 갯수를 구하니 int형으로 ....(?)
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			// 결과는 한 행으로 받기에
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
	
	public ArrayList<Board> selectFreeList(Connection conn, PageInfo pi) {
		
		// select => ResultSet => 몇개가 조회 될 지 모르기에 ArrayList 사용
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFreeList");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 빈sql문 채워주기
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// sql문 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardCategory(rset.getString("BOARD_CATEGORY"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	public int insertFreeBoard(Connection conn, Board b) {
		// insert => dml => int형으로 돌아온다
		
		int result1 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertFreeBoard");
		
		try {
			// pstmt 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// sql문 채운 뒤 결과받기
			pstmt.setInt(1, b.getMemNo());
			pstmt.setString(2, b.getBoardCategory());
			pstmt.setString(3, b.getBoardTitle());
			pstmt.setString(4, b.getBoardContent());
			
			result1 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result1;
	}
	
	
	public int insertFreeAttachment(Connection conn, Attachment at) {
		
		int result2 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertFreeAttachment");
		
		try {
			// pstmt객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// sql문 채우고 결과 실행
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result2 = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result2;
	}
	
	
	public Board detailFreeBoard(Connection conn, int boardNo) {
		
		// 글상세보기 조회를 해야하니 select => ResultSet => 근데 식별자로 검색을 하니 1개만 나온다
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("detailFreeBoard");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardCategory(rset.getString("BOARD_CATEGORY"));
				b.setMemNo(rset.getInt("MEM_NO"));
				b.setBoardName(rset.getString("BOARD_NAME"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setMemId(rset.getString("MEM_ID"));
				b.setGender(rset.getString("GENDER"));
				b.setBirthDate(rset.getString("BIRTH_DATE"));
				b.setGrade(rset.getString("GRADE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	
	public Attachment detailAttachmentBoard(Connection conn, int boardNo) {
		
		// select => ResultSet => pk값으로 1개만 나온다
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("detailAttachmentBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setBoardNo(rset.getInt("BOARD_NO"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		
		// int => dml문 => 결과값은 한행
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// sql문 채우고 실행
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public Board selectFreeBoard(Connection conn, int boardNo) {
		// 한 행의 정보를 가져올것임 SELECT => ResultSet => pk조건으로 1건만조회됨
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFreeBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			
				b = new Board();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setBoardCategory(rset.getString("BOARD_CATEGORY"));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
	
	
	public Attachment selectFreeAttachment(Connection conn, int boardNo) {
		//select
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFreeAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}
	
	
	public int updateFreeBoard(Connection conn, Board b) {
		// update -> 트랜잭션처리
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFreeBoard");
		
		try {
			// pstmt 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// 빈sql채우고 실행하기
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getBoardCategory());
			pstmt.setInt(4, b.getBoardNo());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateFreeAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateFreeAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertFreeNewAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertFreeNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
		// 일단 지워야함	
			pstmt.setInt(1, at.getBoardNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public int deleteFreeBoard(Connection conn, int boardNo) {
		// update -> pk 1행결과 
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteFreeBoard");
		
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
	
	
	
	// 댓글기능
	public ArrayList<Reply> selectFreeReplyList(Connection conn, int boardNo){
		
		ArrayList<Reply> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFreeReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Reply r = new Reply();
				r.setReplyNo(rset.getInt("REPLY_NO"));
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



	public int deleteReply(Connection conn, int replyNo) {
		
		// update문 => 1행 수정
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
	
	
	
	
	
}
