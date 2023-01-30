package com.odw.admin.controller.memberMenu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;

/**
 * Servlet implementation class UpdateReplyStatusController
 */
@WebServlet("/updateReplyStatus.ad")
public class UpdateReplyStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReplyStatusController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int repNo = Integer.parseInt(request.getParameter("rno"));
		String deleteYn = request.getParameter("deleteYn");
		int replyNo = Integer.parseInt(request.getParameter("reno"));
		//System.out.println("댓글로 온 거 맞음");
		
		//System.out.println(repNo);
		//System.out.println(deleteYn);
		//System.out.println(replyNo);
		
		
		// ㅎㅎ 가공 안 함
		// 4) service단 호출
		int result = new MemberMenuService().updateReplyStatus(replyNo, deleteYn);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/reportDetail.ad?rno=" + repNo);
		} else {
			request.getSession().setAttribute("errorMsg", "댓글 숨기기 실패");
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
