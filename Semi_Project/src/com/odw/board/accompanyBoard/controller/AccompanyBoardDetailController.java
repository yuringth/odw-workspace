package com.odw.board.accompanyBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.AccompanyBoardService;
import com.odw.board.model.vo.Accept;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class AccompanyBoardDetailController
 */
@WebServlet("/accompanyDetail.bo")
public class AccompanyBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccompanyBoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 값뽑기
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 조회수 증가 먼저
		AccompanyBoardService bService = new AccompanyBoardService();
		AccompanyBoardService aService = new AccompanyBoardService();
		int result = bService.accompanyBoardIncreaseCount(boardNo);
		
		
		if(result > 0 ) { // 조회수 증가 했을때만 조회
			ArrayList<Accept> acceptList = aService.selectAccompanyBoardAccept(boardNo);
			Board b = bService.selectAccompanyBoard(boardNo);
			
			
			// 여기에 받을 파일 추가하기
			request.setAttribute("b", b);
			request.setAttribute("acceptList", acceptList);
			
			request.getRequestDispatcher("/views/board/accompanyBoard/accompanyBoardDetailView.jsp").forward(request, response);
		}else {
			System.out.println("ㅇㅇ안댐;;");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
