package com.odw.board.controller.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.ReviewBoardService;
import com.odw.board.model.vo.Board;
import com.odw.common.model.vo.PageInfo;


/**
 * Servlet implementation class ReviewBoardListController
 */
@WebServlet("/list.re")
public class ReviewBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewBoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				int listCount; // 현재 일반게시판의 게시글 총 개수 => BOARD로 부터 조회 COUNT(*)활용 (STATUS = 'Y' 인 친구들만)
				int currentPage; // 현재페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cpage")
				int pageLimit; // 페이지 하단에 보여질 페이징바 의 최대갯수 => 10개로 고정 //페이징바 몇개 만들껀지 
				int boardLimit;  // 한 페이지에서 보여질 게시글의 최대 개수 => 10개로 고정
				
				int maxPage; // 가장 마지막 페이지가 몇 번 페이지 인지(총 페이지의 개수)
				int startPage; // 페이지하단에 보여질 페이징바의 시작 수
				int endPage; // 페이지 하단에 보여질 페이징바의 끝 수 
				
			
				listCount = new ReviewBoardService().selectReviewBoardListCount();
				
				currentPage =Integer.parseInt(request.getParameter("cpage"));
			
				pageLimit = 10;
				
				
				boardLimit = 6;
				
				
				maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
				
						
				startPage = (currentPage -1 ) / pageLimit * pageLimit + 1;
				
				
				endPage = startPage + pageLimit -1;
				
				
				if(endPage > maxPage) {
					endPage = maxPage;
				}
				
				
				// 3) 가공
				PageInfo pi = new PageInfo(listCount, currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
				
				// 4) Service로 보내자 ~
				ArrayList<Board> list = new ReviewBoardService().selectReviewBoardList(pi);
				
				// 5) 응답화면 지정
				request.setAttribute("list", list); // 우리가 실제로 조회한 페이지에 보여질 10개의 게시글
				request.setAttribute("pi", pi); // 페이징바를 만들 때 필요한 변수
				
				
				// views/board/boardListView.jsp 화면으로 응답 => 포워딩
				request.getRequestDispatcher("views/board/review/reviewListView.jsp").forward(request, response);
											 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
