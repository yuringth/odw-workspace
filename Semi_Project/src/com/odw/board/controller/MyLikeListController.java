package com.odw.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.odw.board.model.service.BoardService;
import com.odw.board.model.vo.Board;
import com.odw.board.model.vo.Heart;
import com.odw.common.model.vo.PageInfo;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class MyLikeListController
 */
@WebServlet("/myLikeList.bo")
public class MyLikeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLikeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 필요한 변수 세팅
		int listCount; // 좋아요를 누른 게시글 총 개수
		int currentPage; // 현재 페이지
		int pageLimit; // 현재 보이는 페이징바 최대 개수
		int boardLimit; // 한 페이지에 보여질 글의 개수
		
		int maxPage; // 가장 마지막 페이지
		int startPage; // 페이징바 시작 페이지
		int endPage; // 페이징바 마지막 페이지
	
		HttpSession session = request.getSession();
		String memId = ((Member)session.getAttribute("loginUser")).getMemId();
		
		//System.out.println(memId);
		
		// 좋아요 누른 게시글 총 개수
		listCount = new BoardService().myLikeListCount(memId);
		
		// 현재 페이지
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// 보여지는 페이징바 최대 개수
		pageLimit = 10;
		
		// 현재 보여지는 리스트 글의 최대 수
		boardLimit = 10;
		
		// Math.ceil = 올림함수
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		//endPage = startPage + pageLimit - 1;
		
		if(listCount % 10 == 0) {
			endPage = listCount/10; 
		}else {
			endPage = (listCount/10) + 1;
		}
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = new BoardService().myLikeList(pi, memId);
		System.out.println(list);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/myBoard/myLike.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
