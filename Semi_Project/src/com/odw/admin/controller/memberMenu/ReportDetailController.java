package com.odw.admin.controller.memberMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.MemberMenuService;
import com.odw.report.model.vo.Report;

/**
 * Servlet implementation class AdminReportDtailController
 */
@WebServlet("/reportDetail.ad")
public class ReportDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get방식이니까 인코딩 X
		
		// 2) 값 뽑기
		
		int reportNo = Integer.parseInt(request.getParameter("rno"));
		//System.out.println(reportNo);
		
		// 3) 가공 필요 없음
		
		// 4) Service단 부르자
		Report report = new MemberMenuService().selectReport(reportNo);
		
		request.setAttribute("report", report);
		
		request.getRequestDispatcher("views/admin/memberMenu/reportDetail.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
