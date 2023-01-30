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
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/updateMem.me")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST방식
		request.setCharacterEncoding("UTF-8");
		
		// 값뽑기
		String memId = request.getParameter("memId");
		String memName = request.getParameter("memName");
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		String[] phoneArr = request.getParameterValues("phone");
		String email = request.getParameter("email");
		
		String phone = "";
		if(phoneArr != null) {
			phone = String.join("-", phoneArr);
		}
		
		// VO가공
		Member m = new Member();
		m.setMemId(memId);
		m.setMemName(memName);
		m.setAddress(address);
		m.setAddressDetail(addressDetail);
		m.setPhone(phone);
		m.setEmail(email);
		
		// Service에 요청보내기
		Member updateMem = new MemberService().updateMember(m);
		
		// 응답화면 지정
		if(updateMem != null) {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "정보 수정 완료!!");
			session.setAttribute("loginUser", updateMem);
			
			response.sendRedirect(request.getContextPath() + "/myPage.me");
			
		}else {
			request.setAttribute("errorMsg", "회원정보 수정 실패");
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
