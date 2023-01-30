package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;

/**
 * Servlet implementation class UpdateQnaAnswerController
 */
@WebServlet("/updateQnaAnswer.ad")
public class UpdateQnaAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQnaAnswerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값뽑기
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
		String answerContent = request.getParameter("answerContent");
		
		// 3) 가공 X
		
		// 4) service단으로 토스
		int result = new MemberMenuService().updateQnaAnswer(qnaNo, answerContent);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsgQna", "답변 등록 성공");
			response.sendRedirect(request.getContextPath() + "/questionDetail.ad?qno=" + qnaNo);
		} else {
			request.getSession().setAttribute("errorMsg", "답변 등록 실패");
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
