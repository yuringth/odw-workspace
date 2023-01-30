package com.odw.chat.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.chat.model.vo.Chat;
import static com.odw.common.JDBCTemplate.*;
import com.odw.information.model.dao.InformationDao;

public class ChatDao {
	
	private Properties prop = new Properties();
	
	public ChatDao() {
		String fileName = InformationDao.class.getResource("/sql/chat/chat-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Chat selectMemberNow(Connection conn, int chatNo) {
		
		Chat c = new Chat();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberNow");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				c.setMemberCount(rset.getInt("MEMBER_COUNT"));
				c.setMemberNow(rset.getInt("MEMBER_NOW"));
				c.setChatName(rset.getString("BOARD_TITLE"));
				c.setMemId(rset.getString("MEM_ID")); // 작성자
				c.setBoardNo(rset.getInt("BOARD_NO"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return c;
	}

	public ArrayList<Chat> selectAllChatList(Connection conn, int boardNo, String memId) {
		
		ArrayList<Chat> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllChatList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			
			
			while(rset.next()) {
				Chat c = new Chat();
				c.setLoginUser(memId); //나
				c.setMemId(rset.getString("MEM_ID"));
				c.setChatContent(rset.getString("CHAT_CONTENT"));
				c.setChatCreateDate(rset.getString("CHAT_CREATE_DATE"));
				c.setMemberCount(rset.getInt("MEMBER_COUNT"));
				c.setMemberNow(rset.getInt("MEMBER_NOW"));
				c.setChatName(rset.getString("BOARD_TITLE"));
				c.setChatNo(rset.getInt("CHAT_NO"));
				c.setChatStatus(rset.getString("CHAT_STATUS"));
				
				list.add(c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertChatContent(Connection conn, Chat c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertChat");
		//System.out.println(c);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getChatContent());
			pstmt.setInt(2, c.getMemNo());
			pstmt.setString(3, c.getChatName());
			pstmt.setInt(4, c.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Chat> selectChat(Connection conn, int boardNo, String loginUser) {
		
		ArrayList<Chat> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllChatList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Chat c = new Chat();
				c.setLoginUser(loginUser); //나
				c.setMemId(rset.getString("MEM_ID"));
				c.setChatContent(rset.getString("CHAT_CONTENT"));
				c.setChatCreateDate(rset.getString("CHAT_CREATE_DATE"));
				c.setMemberCount(rset.getInt("MEMBER_COUNT"));
				c.setMemberNow(rset.getInt("MEMBER_NOW"));
				c.setChatName(rset.getString("BOARD_TITLE"));
				c.setChatNo(rset.getInt("CHAT_NO"));
				c.setChatStatus(rset.getString("CHAT_STATUS"));
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int deleteChat(Connection conn, int chatNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteChat");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

		
	}

	public int hideChat(Connection conn, int chatNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("hideChat");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}

}
