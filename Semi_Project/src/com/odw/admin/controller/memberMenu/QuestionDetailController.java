package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;
import com.odw.qna.model.vo.Qna;

/**
 * Servlet implementation class AdminQuestionDetailFormController
 */
@WebServlet("/questionDetail.ad")
public class QuestionDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인코딩 필요 없음
		
		// 2) 값 뽑아보자
		int qnaNo = Integer.parseInt(request.getParameter("qno"));
//		System.out.println(qnaNo);
		
		// 3) 가공 X
		
		// 4) service단에 토스
		Qna qna = new MemberMenuService().selectQna(qnaNo);
		
		request.setAttribute("qna", qna);
		request.getRequestDispatcher("views/admin/memberMenu/memberQuestionDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
