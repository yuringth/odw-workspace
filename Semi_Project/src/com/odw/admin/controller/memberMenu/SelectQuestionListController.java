package com.odw.admin.controller.memberMenu;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;
import com.odw.common.model.vo.PageInfo;
import com.odw.qna.model.vo.Qna;

/**
 * Servlet implementation class AdminQuestionFormController
 */
@WebServlet("/question.ad")
public class SelectQuestionListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectQuestionListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) get으로 와서 인코딩 X
		
		// 2) 값뽑기
		String qnaCheck = request.getParameter("check");
		request.setAttribute("qnaCheck", qnaCheck);
		
		if(qnaCheck.equals("all")) {
			qnaCheck = "%";
		} else if(qnaCheck.equals("Y")) {
			qnaCheck = "%Y%";
		} else if(qnaCheck.equals("N")) {
			qnaCheck = "%N%";
		} else{
			qnaCheck = "%";
		}
		
		int currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		int listCount = new MemberMenuService().selectQnaListCountByQnaCheck(qnaCheck);
		
		int pageLimit = 10;
		
		int boardLimit = 10;
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		int startPage = (currentPage -1) / pageLimit * pageLimit + 1;

		int endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 3) 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// 4) Service단에 토스
		ArrayList<Qna> qnaList = new MemberMenuService().selectQnaList(pi, qnaCheck);
		
		// 5) 값 담아서 화면 지정
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/admin/memberMenu/memberQuestionMenu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
