package com.odw.board.accompanyBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.board.model.service.AccompanyBoardService;
import com.odw.board.model.vo.Accept;
import com.odw.board.model.vo.Board;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class AjaxAccompanyCancelController
 */
@WebServlet("/accompanyCancel.bo")
public class AjaxAccompanyCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxAccompanyCancelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		Accept a = new Accept();
		a.setBoardNo(boardNo);
		a.setMemNo(memNo);
		
		Board b = new AccompanyBoardService().accompanyCancel(a);
		
		//응답
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(b, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
