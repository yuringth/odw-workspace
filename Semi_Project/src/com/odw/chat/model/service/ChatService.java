package com.odw.chat.model.service;

import static com.odw.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.chat.model.dao.ChatDao;
import com.odw.chat.model.vo.Chat;

public class ChatService {

	public ArrayList<Chat> selectAllChatList(int boardNo, String memId) {
		
		Connection conn = getConnection();
		
		ArrayList<Chat> list = new ChatDao().selectAllChatList(conn, boardNo, memId);
		
		close(conn);
		
		return list;
	}

	public Chat selectMemberNow(int chatNo) {
		
		Connection conn = getConnection();
		
		Chat c = new ChatDao().selectMemberNow(conn, chatNo);
		
		close(conn);
		
		return c;
	}

	public int insertChat(Chat c) {
		
		Connection conn = getConnection();
		
		int result = new ChatDao().insertChatContent(conn, c);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public ArrayList<Chat> selectChat(int boardNo, String loginUser) {
		
		Connection conn = getConnection();
		
		ArrayList<Chat> list = new ChatDao().selectChat(conn, boardNo, loginUser);
		
		close(conn);
		
		return list;
	}

	public int deleteChat(int chatNo) {

		Connection conn = getConnection();
		
		int result = new ChatDao().deleteChat(conn, chatNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	public int hideChat(int chatNo) {
		
		Connection conn = getConnection();
		
		int result = new ChatDao().hideChat(conn, chatNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

}
