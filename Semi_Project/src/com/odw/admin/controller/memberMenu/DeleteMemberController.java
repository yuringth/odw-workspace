package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;

/**
 * Servlet implementation class DeleteMemberController
 */
@WebServlet("/deleteMember.ad")
public class DeleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) post 방식 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값뽑기
		String userId = request.getParameter("userId");
		int repNo = Integer.parseInt(request.getParameter("rno"));
		
//		System.out.println(userId);
//		System.out.println(repNo);
		
		// 3)  가공 X
		
		// 4) service단에 토스
		int result = new MemberMenuService().deleteMemberByMemId(userId, repNo);
//		System.out.println(result);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsgReport", "회원 탈퇴 완료");
			response.sendRedirect(request.getContextPath() + "/reportDetail.ad?rno=" + repNo);
		} else {
			request.getSession().setAttribute("errorMsg", "회원 탈퇴를 실패하였습니다.");
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
