package com.odw.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.chat.model.service.ChatService;
import com.odw.chat.model.vo.Chat;

/**
 * Servlet implementation class ChatInsertController
 */
@WebServlet("/chatInsert.ch")
public class ChatInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int chatNo = Integer.parseInt(request.getParameter("cno"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String chatContent = request.getParameter("content");
		String chatName = request.getParameter("chatName");
		
		Chat c = new Chat();
		c.setBoardNo(chatNo);
		c.setMemNo(memNo);
		c.setChatContent(chatContent);
		c.setChatName(chatName);
		
		int result = new ChatService().insertChat(c);
		
		//응답
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(result, response.getWriter());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
