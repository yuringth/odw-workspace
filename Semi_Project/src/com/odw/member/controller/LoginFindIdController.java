package com.odw.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.member.model.service.MemberService;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class LoginFindIdController
 */
@WebServlet("/find.id")
public class LoginFindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFindIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memName = request.getParameter("findName");
		String email = request.getParameter("findEmail");
		
		String originId = new MemberService().findId(memName, email);
		
		if(originId != null) {
		
			String newId = null;
	    
			if(originId.length() < 7) {
				newId = originId.replace(originId.substring(originId.length()-2, originId.length()), "**");
			}else {
				newId = originId.replace(originId.substring(originId.length()-3, originId.length()), "***");
			}

			request.setAttribute("newId", newId);
			request.getRequestDispatcher("views/user/findId.jsp").forward(request, response);
		
		}else {
			
			request.getRequestDispatcher("views/user/notFoundId.jsp").forward(request, response);
			
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
