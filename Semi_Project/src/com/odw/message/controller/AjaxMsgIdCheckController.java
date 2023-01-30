package com.odw.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.member.model.service.MemberService;
import com.odw.message.model.service.MessageService;

/**
 * Servlet implementation class AjaxCheckController
 */
@WebServlet("/msgRecipientCheck.me")
public class AjaxMsgIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMsgIdCheckController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkId = request.getParameter("checkId");
		
		int count = new MessageService().msgIdCheck(checkId);
		
		response.setContentType("text/html; charset=UTF-8");
		

		if(count > 0) { // 회원이 존재할 때
			
			response.getWriter().print("NNNNY");
			
			
		
		}else { // 회원이 존재하지 않을때
		
			response.getWriter().print("NNNNN");
		
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
