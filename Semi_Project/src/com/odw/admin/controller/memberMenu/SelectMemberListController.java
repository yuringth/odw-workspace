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
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class AdminSelectFormController
 */
@WebServlet("/select.ad")
public class SelectMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMemberListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String condition = request.getParameter("condition");
		request.setAttribute("condi", condition);
		// System.out.println(condition);
		
		if(condition.equals("all")) {
			condition = "%";
		} else if(condition.equals("Y")) {
			condition = "%Y%";
		} else if(condition.equals("N")) {
			condition = "%N%";
		} else {
			condition = "";
		}
		
		// * listCount : 총 게시물의 수
		listCount = new MemberMenuService().selectMemberListCountByCondition(condition);
//		System.out.println(listCount);
		
		// * currentPage : 현재 페이지(== 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage")); // 1
		
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
		
		
		
		// System.out.println(condition);
		
		// 7가지 변수 만들기 완료
		// 3) 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// 4) Service단에 토스
		ArrayList<Member> memberList = new MemberMenuService().selectMemberList(pi, condition);
		
		// 5) 값 담고 응답화면 지정
		request.setAttribute("memberList", memberList);
		request.setAttribute("pi", pi);
		request.setAttribute("condition", condition);
		request.getRequestDispatcher("views/admin/memberMenu/memberSelectMenu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
