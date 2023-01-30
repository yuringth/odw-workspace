package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;

/**
 * Servlet implementation class DeleteBoardController
 */
@WebServlet("/updateBoardStatus.ad")
public class UpdateBoardStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoardStatusController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) get 방식 인코딩 생략
		// 2) 값뽑기
		int repNo = Integer.parseInt(request.getParameter("rno"));
		String deleteYn = request.getParameter("deleteYn");
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		
		// ㅎㅎ 가공 안 함
		// 4) service단 호출
		int result = new MemberMenuService().updateBoardStatus(boardNo, deleteYn);
		
		if(result > 0) {
			
			response.sendRedirect(request.getContextPath() + "/reportDetail.ad?rno=" + repNo);
		} else {
			request.getSession().setAttribute("errorMsg", "게시물 숨기기 실패");
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
