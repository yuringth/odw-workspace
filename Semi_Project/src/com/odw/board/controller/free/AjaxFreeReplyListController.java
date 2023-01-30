package com.odw.board.controller.free;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.board.model.service.FreeService;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class AjaxFreeReplyListController
 */
@WebServlet("/rlist.fr")
public class AjaxFreeReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFreeReplyListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// get방식 => 왜??
		
		// 값뽑기 => 글번호 => 댓글은 글안에 종속
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	
		// vo가공
		
		// service요청
		ArrayList<Reply> list = new FreeService().selectFreeReplyList(boardNo);
		
		// list를 응답
		// GSON을 이용 => ArrayList를 자바스크립트의 객체배열형태로 변환
		// 형식, 인코딩
		response.setContentType("application/json; charset=UTF-8"); 
		new Gson().toJson(list, response.getWriter());
		// => Gson 따로 키값을 지정안하면 키값 == 필드명
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
