package com.odw.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.chat.model.service.ChatService;
import com.odw.chat.model.vo.Chat;

/**
 * Servlet implementation class ChatSelectController
 */
@WebServlet("/chatSelect.ch")
public class ChatSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatSelectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("cno"));
		String loginUser = request.getParameter("memId");
		
		ArrayList<Chat> list = new ChatService().selectChat(boardNo, loginUser);
		
		//응답
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
