package com.odw.admin.controller.infoMenu;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.InfoMenuService;
import com.odw.common.model.vo.PageInfo;
import com.odw.information.model.vo.Information;

/**
 * Servlet implementation class AdminSeasonFormController
 */
@WebServlet("/season.ad")
public class SelectSeasonListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSeasonListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩  X, 값 X, 가공 X
		
		// 1) 페이징 처리를 위해 get 방식으로 받아왔고 인코딩은 필요 없음.
		// 2) 페이징 값 뽑아오기
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		
		// 페이징 처리
		// 필요한 변수들 선언
		int listCount; // 현재 일반게시판의 게시글 총 갯수 => 테이블로부터 조회 COUNT(*)활용 (관리자 화면은 STATUS가 무엇이든 상관 없이 다 불러옴)
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cPage")
		int pageLimit; // 페이지 하단에 보여질 페이징바의 최대 개수 => 10개 고정
		int boardLimit; // 현 페이지에 보여질 게시물의 최대 개수 => 10개로 고정
		
		int maxPage; // 가장 마지막 페이지가 몇 번째 페이지인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이지바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이지바의 마지막 수
		
		String deleteYn = request.getParameter("deleteYn");
		String season = request.getParameter("season");
		request.setAttribute("page", cpage);

		if(deleteYn == null) {
			deleteYn = "all";
		}
		
		if(season == null) {
			season  = "all";
		}
		
		request.setAttribute("infoDeleteYn", deleteYn);
		request.setAttribute("infoSeason", season);
		
		switch(deleteYn) {
		case "Y" : deleteYn = "%Y%";
		break;
		case "N" : deleteYn = "%N%";
		break;
		case "all" : deleteYn = "%";
		}
		
		switch(season) {
		case "spring" : 
		case "봄" : season = "%봄%";
		break;
		case "summer" :
		case "여름" : season = "%여름%";
		break;
		case "fall" :
		case "가을" : season = "%가을%";
		break;
		case "winter" :
		case "겨울" : season = "%겨울%";
		break;
		case "all" : season = "%";
		}
		
		Information info = new Information();
		info.setInfoDeleteYn(deleteYn);
		info.setSeason(season);
		
		
		// * listCount : 총 게시물의 수
		listCount = new InfoMenuService().selectSeasonListCount(info);
		// System.out.println(listCount);
		
		// * currentPage : 현재 페이지(== 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage")); // 1
		// System.out.println(currentPage);
		
		// * pageLimit : 페이징바의 최대 갯수
		pageLimit = 10;
		
		// * boardLmit : 한 페이지에 보여질 게시글의 최대 개수
		boardLimit = 10;

		maxPage = (int)Math.ceil((double)listCount / boardLimit);

		startPage = (currentPage -1) / pageLimit * pageLimit + 1;

		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 7가지 변수 만들기 완료
		// 3) 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// service단 호출
		ArrayList<Information> infoList = new InfoMenuService().selectSeasonInfoList(pi, info);
		
		request.setAttribute("infoList", infoList);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/admin/infoMenu/seasonMenu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
