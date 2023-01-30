package com.odw.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.odw.member.model.service.MemberService;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class DeleteMemController
 */
@WebServlet("/deleteMemFinish.me")
public class DeleteMemFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
			// 1) POST방식
			request.setCharacterEncoding("UTF-8");
			
			
			// 2) 값 뽑기
			String memPwd = request.getParameter("memPwd");
			
			HttpSession session = request.getSession();
			String memId = ((Member)session.getAttribute("loginUser")).getMemId();
			// 3) 값 담기 => 값이 적어서 패스
			
			// 4) Service에 요청보내기
			
			Member updateMem = new MemberService().deleteMem(memId, memPwd);
			
			
			// 5) 응답 화면 지정
			if(updateMem != null) { // 회원 탈퇴 성공
				request.getRequestDispatcher("views/myPage/deleteMemCheck.jsp").forward(request, response);
				session.removeAttribute("loginUser");
				
				
			}else { // 회원탈퇴 실패
				request.setAttribute("errorMsg", "회원 탈퇴 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
