package com.odw.message.model.dao;

import static com.odw.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odw.common.model.vo.PageInfo;
import com.odw.message.model.vo.Message;

public class MessageDao {
	
	private Properties prop = new Properties();
	
	public MessageDao() {
		
		String file = MessageDao.class.getResource("/sql/message/message-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
/*
 * 메세지 보내기 전 회원 존재하는지 확인하는 메소드
 */
	
	public int msgIdCheck(Connection conn, String checkId) {
		
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("msgIdCheck");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				count = rset.getInt("COUNT(*)");
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	

	
/*
 * message보낼때 적은 회원 아이디와 동일한 회원번호 받아오는 메소드
*/
		
	
	public int findSenderNo(Connection conn, Message msg) {
		
		int recipient = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("findSenderNo");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, msg.getRecipient());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				recipient = rset.getInt("MEM_NO");
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return recipient;
	}
	
	
	
	
	
/*	
 * 메세지를 테이블에 넣는 메소드
 */
	
	public int insertMessage(Connection conn, Message msg) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMessage");
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, msg.getMsgContent());
			
			pstmt.setString(2, msg.getMemNo());
			
			pstmt.setInt(3, Integer.parseInt(msg.getRecipient()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
		
	}
		
		
	
	
/*
 * 받은메시지함 페이징처리 메소드	
*/
	
	
	public int selectReceiveListCount(Connection conn, int reciepient){
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReceiveListCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reciepient);
			
			rset = pstmt.executeQuery();
			
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

	
	
	
	
	
	
/*
 * 받은쪽지함 리스트 정보 가져오는 메소드	
 */
		
	
	
	public ArrayList<Message> receiveListMessage(Connection conn, PageInfo pi, int reciepient){
		
		
		ArrayList<Message> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("receiveListMessage");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			
			pstmt.setInt(1, reciepient);
			
			pstmt.setInt(2, startRow);
			
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Message msg = new Message();
				
				msg.setMsgNo(rset.getInt("MSG_NO"));
				msg.setMemNo(rset.getString("MEM_ID"));
				msg.setMsgContent(rset.getString("MSG_CONTENT"));
				msg.setMsgCreateDate(rset.getString("MSG_CREATE_DATE"));
	
				
				list.add(msg);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	
	
	
	
	
	
	
	
	
/*
 * 받은쪽지함 디테일뷰 가져오는 메소드	
 */
	
	public Message receiveDetailViewMessage(Connection conn, int msgNo) {
		
		Message msg = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("receiveDetailViewMessage");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, msgNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				msg = new Message();
				
				msg.setMsgContent(rset.getString("MSG_CONTENT"));
				msg.setMsgCreateDate(rset.getString("MSG_CREATE_DATE"));
				msg.setMemNo(rset.getString("MEM_ID"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		return msg;
		
	}
	
	
	
	

/*
 * 메세지 삭제하는 메소드
 */
	
	
	public int deleteMessage(Connection conn, int msgNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMessage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, msgNo);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	

	
	
	
/*
 * 보낸 메세지 페이징 처리하는 메소드
 */
	
	public int selectSendListCount(Connection conn, int sender) {
		
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSendListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sender);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				listCount = rset.getInt("COUNT(*)");
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			close(rset);
			close(pstmt);
			
		}
		
		return listCount;
		
		
		
	}
	
	
	
	
	
	
/*
 * 보낸메세지 리스트 정보 가져오는 메소드
 */
	
	public ArrayList<Message> sendListMessage(Connection conn, PageInfo pi, int sender){
	
		ArrayList<Message> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("sendListMessage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sender);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(2, startRow);
			
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Message msg = new Message();
				
				msg.setMsgNo(rset.getInt("MSG_NO"));
				msg.setRecipient(rset.getString("MEM_ID"));
				msg.setMsgContent(rset.getString("MSG_CONTENT"));
				msg.setMsgCreateDate(rset.getString("MSG_CREATE_DATE"));
	
				
				list.add(msg);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}

	
	
	
	
	

/*
 * 보낸메세지 디테일뷰 가져오는 메소드	
 */
	
	public Message sendDetailViewMessage(Connection conn, int msgNo) {
		
		Message msg = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("sendDetailViewMessage");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, msgNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				msg = new Message();
				
				msg.setMsgContent(rset.getString("MSG_CONTENT"));
				msg.setMsgCreateDate(rset.getString("MSG_CREATE_DATE"));
				msg.setMemNo(rset.getString("MEM_ID"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		return msg;
		
	}
		


}
