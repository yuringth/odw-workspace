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
import com.odw.report.model.vo.Report;

/**
 * Servlet implementation class AdminMainFormController
 */
@WebServlet("/main.ad")
public class AdminMainFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMainFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = 1;
		
		int listCount = new MemberMenuService().selectMemberListCountByColumn("REPORT");
		
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
		ArrayList<Report> reportList = new MemberMenuService().selectReportList(pi, "REP_NO", "%");
		ArrayList<Qna> qnaList = new MemberMenuService().selectQnaList(pi, "%");

		
		// 5) 화면 지정
		request.setAttribute("reportList", reportList);
		request.setAttribute("qnaList", qnaList);

		
		
		request.getRequestDispatcher("views/admin/adminMainForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
