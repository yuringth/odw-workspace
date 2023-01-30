package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;

/**
 * Servlet implementation class DeleteQnaAnswerController
 */
@WebServlet("/deleteQnaAnswer.ad")
public class DeleteQnaAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQnaAnswerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] qnaNoList = request.getParameterValues("qnaNo");
		
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		String check = request.getParameter("check");
		
		int result = new MemberMenuService().deleteQnaAnswer(qnaNoList);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "답변 삭제 완료");
			response.sendRedirect(request.getContextPath() + "/question.ad?cpage=" + cpage + "&check=" + check);
		} else {
			request.getSession().setAttribute("errorMsg", "답변 삭제 실패");
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
