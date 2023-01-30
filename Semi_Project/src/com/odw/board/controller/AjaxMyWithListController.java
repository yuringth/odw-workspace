package com.odw.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.odw.board.model.service.BoardService;
import com.odw.board.model.vo.Board;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class AjaxMyWithList
 */
@WebServlet("/aMyWithList.bo")
public class AjaxMyWithListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMyWithListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String listInfo = request.getParameter("listInfo");
		ArrayList<Board> list;
		
		HttpSession session = request.getSession();
		int memNo = ((Member)session.getAttribute("loginUser")).getMemNo();
		
		
		if(listInfo.equals("btn1")) {
			//신청내역 전체조회
			list = new BoardService().myWithList(memNo);
		}else {
			//모집내역 전체조회
			list = new BoardService().myRecruitList(memNo);
		}
		System.out.println(list.toString());
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(list, response.getWriter());
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
