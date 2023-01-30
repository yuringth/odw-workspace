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
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/enroll.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String memName = request.getParameter("memName");
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		String phone = request.getParameter("phoneFirst") + "-" + request.getParameter("phoneSecond") + "-" + request.getParameter("phoneThird");
		String email = request.getParameter("email");
		String birthDate = request.getParameter("birthDate");
		String gender = request.getParameter("gender");
		
		Member member = new Member();
		
		member.setMemId(memId);
		member.setMemPwd(memPwd);
		member.setMemName(memName);
		member.setAddress(address);
		member.setAddressDetail(addressDetail);
		member.setPhone(phone);
		member.setEmail(email);
		member.setBirthDate(birthDate);
		member.setGender(gender);
		
		int result = new MemberService().insertMember(member);
		
		if(result > 0) {
			
			request.setAttribute("memId", memId);
			request.getRequestDispatcher("views/user/successInsertMember.jsp").forward(request, response);
			
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
