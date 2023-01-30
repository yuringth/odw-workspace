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
import com.odw.report.model.vo.Report;

/**
 * Servlet implementation class AdminReportFormController
 */
@WebServlet("/report.ad")
public class SelectReportListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectReportListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식 인코딩 스킵
		
		// 2) 값뽑기
		
		String table = request.getParameter("table");
		
		String repYn = request.getParameter("repYn");
		
		request.setAttribute("repType", table);
		request.setAttribute("status", repYn);
		
		
		//System.out.println(table);
		
		if(repYn.equals("all")) {
			repYn = "%";
		} else if(repYn.equals("Y")) {
			repYn = "%Y%";
		} else if(repYn.equals("N")) {
			repYn = "%N%";
		} else {
			repYn = "%";
		}
		
		int currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		int listCount = new MemberMenuService().selectReportCountList(table, repYn);
		
		if(table.equals("BOARD_NO")) {
			table = "R.BOARD_NO";
		}
		
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
		ArrayList<Report> reportList = new MemberMenuService().selectReportList(pi, table, repYn);
		
		// 5) 화면 지정
		request.setAttribute("pi", pi);
		request.setAttribute("reportList", reportList);
		
		request.getRequestDispatcher("views/admin/memberMenu/reportMenu.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
