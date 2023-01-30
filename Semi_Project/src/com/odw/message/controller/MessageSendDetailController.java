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
 * Servlet implementation class MessageSendDetailController
 */
@WebServlet("/sendDetail.msg")
public class MessageSendDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageSendDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		
		Message msg = new MessageService().sendDetailViewMessage(msgNo);
		
		msg.setMsgNo(msgNo);
		
		if(msg != null) {

			request.setAttribute("msg", msg);
			request.getRequestDispatcher("views/message/messageSendDetail.jsp").forward(request, response);
			
		}else {
			
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
