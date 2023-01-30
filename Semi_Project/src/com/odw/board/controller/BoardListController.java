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
import com.odw.common.model.vo.PageInfo;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class myWriteController
 */
@WebServlet("/myWriteList.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int listCount; 		// 게시글의 수
	int currentPage;	// 현재 페이지
	int pageLimit; 		// 페이징바 최대 개수
	int boardLimit;		// 한 페이지에 보여질 게시글 최대 개수
	
	int maxPage; 		// 가장 마지막에 오는 페이지 번호
	int startPage; 		// 페이징바의 시작을 나타내는것
	int endPage;
	
	HttpSession session = request.getSession();
	String memId = ((Member)session.getAttribute("loginUser")).getMemId();
	
	listCount = new BoardService().selectListCount(memId);
	
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
		endPage = listCount / 10; 
	}else {
		endPage = (listCount / 10) + 1;
	}
	
	PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
	
	ArrayList<Board> list = new BoardService().selectMyBoard(pi, memId);
	
	// 응답화면 지정
	
	request.setAttribute("list", list);
	request.setAttribute("pi", pi);
	
	request.getRequestDispatcher("views/myBoard/myBoardList.jsp").forward(request, response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
