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
 * Servlet implementation class UpdateMemPwdController
 */
@WebServlet("/updatePwd.me")
public class UpdateMemPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemPwdController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// POST방식 => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2.값뽑기
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String updatePwd = request.getParameter("updatePwd");
	
		// 3. VO객체로 가공하기 => 너무 적어서 패스
		
		// 4. Service로 요청보내기
		 Member updateMem= new MemberService().updatePwd(memId, memPwd, updatePwd);
		
		// 5. 응답화면 지정
		 HttpSession session = request.getSession();
		 
		 if(updateMem == null) { // 실패
			 session.setAttribute("alertMsg", "비밀번호 변경 실패");
		 }else { // 성공
			 session.setAttribute("alertMsg", "비밀번호 변경 성공!");
			 session.setAttribute("loginUser", updateMem);
			 
		 }
		 
		 // 실패든 성공이든 마이페이지로 무조건 돌아가기
		 response.sendRedirect(request.getContextPath() + "/myPage.me");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
