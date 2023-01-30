package com.odw.board.accompanyBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.AccompanyBoardService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class AccompanyBoardController
 */
@WebServlet("/accompanyBoardList.bo")
public class AccompanyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccompanyBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//리스트 가져와서 화면에 뿌려야함
		
		ArrayList<Board> list = new AccompanyBoardService().selectAccompanyBoardList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/accompanyBoard/accompanyBoardListView.jsp").forward(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
