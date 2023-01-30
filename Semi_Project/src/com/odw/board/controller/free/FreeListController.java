package com.odw.board.controller.free;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.FreeService;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;


// 자유게시판 클릭하면 1페이지의 자유게시판 목록을 보여줘
/**
 * Servlet implementation class FreeListController
 */
@WebServlet("/list.fr")
public class FreeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징처리
		// 필요한 변수들
		int listCount; // 게시글의 총 수
		int currentPage; // 현재 페이지
		int pageLimit; // 페이지 하단에 보여질 페이징바의 수
		int boardLimit; // 한 페이지의 게시글의 최대 갯수
		
		int maxPage; // 마지막 페이지 갯수
		int startPage; // 페이지 하단에 보이질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여지는 페이징바의 끝 수
		
		
		// listCount : 총 게시글의 수
		listCount = new FreeService().selectListCount();		
		
		// * currentPage : 현재 페이지 (== 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// * pageLimit : 페이징바의 최대 개수
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 개수
		boardLimit = 10;
		
		// 마지막 페이지 개수
		maxPage = (int)Math.ceil((double)listCount/boardLimit);

		// 페이징바의 시작 수
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		// 페이징바의 끝 수
		endPage = startPage + pageLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 변수 7개를 만들었음 => PageInfo VO객체를 만들자!
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

		// Service 단으로 보낸다.
		ArrayList<Board> list = new FreeService().selectFreeList(pi);
		
		// 응답화면 지정
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		
		// 1) 인코딩 X => a태그요청은 GET방식 => 단순 자유게시판 클릭이니까 전달값이 없다.
		// 2) 값뽑기 => 없음
		// 3) 값이 없어서 가공할 것도 없음
		
//		new FreeService().selectList();
		request.getRequestDispatcher("/views/board/free/freeListView.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
