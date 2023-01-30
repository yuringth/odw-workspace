package com.odw.message.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.odw.common.model.vo.PageInfo;
import com.odw.member.model.dao.MemberDao;
import com.odw.message.model.dao.MessageDao;
import com.odw.message.model.vo.Message;

import static com.odw.common.JDBCTemplate.*;

public class MessageService {
	
	
	
/*
 * 	메세지를 보낼 회원의 아이디가 존재하는지 찾는 메소드
 */
	
	
	public int msgIdCheck(String checkId) {
		
		Connection conn = getConnection();
		
		int count = new MessageDao().msgIdCheck(conn, checkId);
		
		close(conn);
		
		return count;
		
	}
	
	
	
	
	
/*
 * message보낼때 적은 회원 아이디와 동일한 회원번호 받아오는 메소드
 */

	
	public int findSenderNo(Message msg) {
		
		Connection conn = getConnection();
		
		int recipient = new MessageDao().findSenderNo(conn, msg);
		
		close(conn);
		
		return recipient;
	}
	
	
	
	
/*
 * 	메세지를 테이블에 넣는 메소드
*/	
	public int insertMessage(Message msg) {
		
		Connection conn = getConnection();
		
		int result = new MessageDao().insertMessage(conn, msg);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

	
	
	
	
	
/*
 * 받은메세지 페이징 처리하는 메소드
 */
	
	public int selectReceiveListCount(int reciepient) {
		
		Connection conn = getConnection();
		
		int listCount = new MessageDao().selectReceiveListCount(conn, reciepient);
		
		close(conn);
		
		return listCount;
		
	}

	
	
	
	
	
	
	
/*
 * 받은메세지 리스트 정보 가져오는 메소드	
 */
	
	public ArrayList<Message> receiveListMessage(PageInfo pi, int recipient){
		
		Connection conn = getConnection();
		
		ArrayList<Message> msg = new MessageDao().receiveListMessage(conn, pi, recipient);
		
		close(conn);
		
		return msg;
		
	}
	
	
	
	
/*
 * 받은메세지 디테일뷰 가져오는 메소드	
 */
	


	public Message receiveDetailViewMessage(int msgNo) {
		
		Connection conn = getConnection();
		
		Message msg = new MessageDao().receiveDetailViewMessage(conn, msgNo);
		
		close(conn);
		
		return msg;
		
		
	}
	
	
	
/*
 * 메세지 삭제하는 메소드	
 */
	
	
	public int deleteMessage(int msgNo) {
		
		Connection conn = getConnection();
		
		int result = new MessageDao().deleteMessage(conn, msgNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
	
/*
 * 보낸 메세지 페이징 처리하는 메소드
 */
	
	
	public int selectSendListCount(int sender) {
		
		Connection conn = getConnection();
		
		int listCount = new MessageDao().selectSendListCount(conn, sender);
		
		close(conn);
		
		return listCount;
		
		
	}
	
	
	
	
	
/*
 * 보낸메세지 리스트 정보 가져오는 메소드	
 */
	
	public ArrayList<Message> sendListMessage(PageInfo pi, int sender){
		
		Connection conn = getConnection();
		
		ArrayList<Message> list = new MessageDao().sendListMessage(conn, pi, sender);
		
		close(conn);
		
		return list;
		
	}
	

	
/*
 * 받은메세지 디테일뷰 가져오는 메소드	
 */
	


	public Message sendDetailViewMessage(int msgNo) {
		
		Connection conn = getConnection();
		
		Message msg = new MessageDao().sendDetailViewMessage(conn, msgNo);
		
		close(conn);
		
		return msg;
		
		
	}	

}









// 충원님




