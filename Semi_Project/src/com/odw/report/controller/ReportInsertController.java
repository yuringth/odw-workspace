package com.odw.report.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.report.model.service.ReportService;
import com.odw.report.model.vo.Report;

/**
 * Servlet implementation class ReportInsertController
 */
@WebServlet("/reportInsert.fr")
public class ReportInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 post 
		request.setCharacterEncoding("UTF-8");
		 
		// 2) 값 뽑기
		// 옵션, 사유
		String repReason = request.getParameter("repReason"); // 배열인데 어케 뽑히지?
		String repContent = request.getParameter("repContent");
		
		// 3) vo객체에 담기
		Report r = new Report();
		r.setRepReason(repReason);
		r.setRepContent(repContent);
		
		// 4) Service로 보내자
		int result = new ReportService().insertReport(r);
		
		// 5) 응답화면 지정
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "신고 성공?");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
			
		} else {
			
			request.getSession().setAttribute("alertMsg", "게시글 작성 실패");
			request.getRequestDispatcher("views/common/errorPage").forward(request, response);
			
		}
		
		
		
		
		
		
		
//		request.getRequestDispatcher("views/board/free/freeListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
