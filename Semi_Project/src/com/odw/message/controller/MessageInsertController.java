package com.odw.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.message.model.service.MessageService;
import com.odw.message.model.vo.Message;

/**
 * Servlet implementation class MessageInsertController
 */
@WebServlet("/insert.msg")
public class MessageInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memNo = request.getParameter("memNo");
		
		String msgContent = request.getParameter("messageContent");
		
		String msgRecipient = request.getParameter("msgRecipient");
		
		Message msg = new Message();
		
		msg.setMemNo(memNo);
		
		msg.setMsgContent(msgContent);
		
		msg.setRecipient(msgRecipient);
		
		//회원 아이디와 동일한 회원번호를 받아오는 메소드
		
		int recipient = new MessageService().findSenderNo(msg);
		
		
		if(recipient > 0) {
			
			msg.setRecipient(Integer.toString(recipient));
			
			int result = new MessageService().insertMessage(msg);
			
			if(result > 0) {
				
				response.sendRedirect(request.getContextPath() + "/receiveList.msg?cpage=1&memNo=" + memNo);
			
			} else {
				
				response.sendRedirect(request.getContextPath() + "/receiveList.msg?cpage=1&memNo=" + memNo);
				
			}
			
		} else {
			
			response.sendRedirect(request.getContextPath() + "/receiveList.msg?cpage=1&memNo=" + memNo);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
