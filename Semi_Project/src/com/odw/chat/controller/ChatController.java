package com.odw.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.chat.model.service.ChatService;
import com.odw.chat.model.vo.Chat;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class AccompanyChatController
 */
@WebServlet("/accompanyChat.bo")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("cno"));
		String memId = ((Member)request.getSession().getAttribute("loginUser")).getMemId();
		
		ArrayList<Chat> list = new ChatService().selectAllChatList(boardNo, memId);
		Chat c = new ChatService().selectMemberNow(boardNo);
		
		
		request.setAttribute("list", list);
		request.setAttribute("c", c);
		request.getRequestDispatcher("views/chat/chatView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
