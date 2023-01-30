package com.odw.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.BoardService;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;

/**
 * Servlet implementation class MYReplyController
 */
@WebServlet("/MyReplyy.bo")
public class MYReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MYReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("들어왔나");
		
		int listCount; 		// 게시글의 수
		int currentPage;	// 현재 페이지
		int pageLimit; 		// 페이징바 최대 개수
		int boardLimit;		// 한 페이지에 보여질 게시글 최대 개수
		
		int maxPage; 		// 가장 마지막에 오는 페이지 번호
		int startPage; 		// 페이징바의 시작을 나타내는것
		int endPage;
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		System.out.println(memNo);
		
		
		listCount = new BoardService().selectReplyListCount(memNo);
		
		System.out.println(listCount);
		
		// currentPage == 사용자가 요청한 페이지
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		// 페이징바의 최대 개수
		pageLimit = 10;
		
		// 한 페이지에서 보여줄 게시글의 갯수
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		//endPage = startPage + pageLimit - 1;
		if(listCount % 10 == 0) {
			endPage = listCount/10; 
		}else {
			endPage = (listCount/10) + 1;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = new BoardService().selectMyReply(pi, memNo);
		
		// 응답화면 지정
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/myBoard/myReply.jsp").forward(request, response);
		
		
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
