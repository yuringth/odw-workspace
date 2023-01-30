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
 * Servlet implementation class LoginFindPwdController
 */
@WebServlet("/find.pwd")
public class LoginFindPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFindPwdController() {
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
		String memId = request.getParameter("findId");
		
		Member m = new Member();
		
		m.setMemName(memName);
		m.setEmail(email);
		m.setMemId(memId);
		
		String memPwd = new MemberService().findPwd(m);
		
		if(memPwd != null) {
			
			String originPwd = memPwd;
		      
			String newPwd = null;
		    
		    if(originPwd.length() < 7) {
		    	newPwd = originPwd.replace(originPwd.substring(originPwd.length()-2, originPwd.length()), "**");
		    }else {
		    	newPwd = originPwd.replace(originPwd.substring(originPwd.length()-3, originPwd.length()), "***");
		    }

			request.setAttribute("newPwd", newPwd);
			request.getRequestDispatcher("views/user/findPwd.jsp").forward(request, response);
			
			
			
		}else {
		
			request.getRequestDispatcher("views/user/notFoundPwd.jsp").forward(request, response);
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
