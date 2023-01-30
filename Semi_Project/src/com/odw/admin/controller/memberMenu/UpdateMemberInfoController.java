package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberInfoController
 */
@WebServlet("/updateMemberInfo.ad")
public class UpdateMemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String birthDate = request.getParameter("birthDate");
		String grade = request.getParameter("grade");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String addressDetail = request.getParameter("addressDetail");
		
		Member mem = new Member();
		mem.setMemNo(memNo);
		mem.setBirthDate(birthDate);
		mem.setGrade(grade);
		mem.setPhone(phone);
		mem.setEmail(email);
		mem.setAddress(address);
		mem.setAddressDetail(addressDetail);
		
		int result = new MemberMenuService().updateMemberInfo(mem);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsgMem", "회원 정보 수정 완료");
			response.sendRedirect(request.getContextPath() + "/memberDetail.ad?mno=" + memNo);
		} else {
			request.getSession().setAttribute("errorMsg", "회원 정보 수정 실패");
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
