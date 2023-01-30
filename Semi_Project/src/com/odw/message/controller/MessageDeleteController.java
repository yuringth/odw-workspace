package com.odw.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.message.model.service.MessageService;

/**
 * Servlet implementation class MessageDeleteController
 */
@WebServlet("/delete.msg")
public class MessageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		
		String memNo = request.getParameter("memNo");
		
		int result = new MessageService().deleteMessage(msgNo);
		
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath() + "/receiveList.msg?cpage=1&memNo=" + memNo);
			
		} else {
			
			request.getRequestDispatcher("views/common/error500.jsp").forward(request, response);
			
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
