package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;

/**
 * Servlet implementation class UpdateRepYnController
 */
@WebServlet("/updateRepYn.ad")
public class UpdateRepYnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRepYnController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식 인코딩 X
		// 2) 값뽑자
		int repNo = Integer.parseInt(request.getParameter("rno"));
		
		// 3) 가공 X
		
		// 4) 바로 service단 호출
		
		int result = new MemberMenuService().updateRepYn(repNo);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsgReport", "처리완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/reportDetail.ad?rno=" + repNo);
		} else {
			request.getSession().setAttribute("errorMsg", "신고 기각을 실패하였습니다. 재시도해주세요.");
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
