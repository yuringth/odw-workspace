package com.odw.board.controller.feed;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.board.model.service.FeedService;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class AjaxFeedReplySelecetController
 */
//댓글 목록 조회 기능
@WebServlet("/rlist.fe")
public class AjaxFeedReplySelecetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFeedReplySelecetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) get방식 => 인코딩 없음
		// 2) 값뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		// 3) 값 담기 => 패스
		
		// 4) 서비스단으로 넘겨주기
		ArrayList<Reply> list = new FeedService().selectFeedReply(boardNo);
		
//		 System.out.println(list); // [Reply [Reply toString, Reply toString,..]
		
		// 5) 응답화면 지정시, list는 자바객체며, 내가 지정한 응답화면은 자바스크립트의 응답화면이기때문에 자바객체를 자바스크릅트안에서 사용가능하게 인코딩 작업을 해줘야함
		// GSON을 편하게 이용해보자 => ArrayList를 자바스크립트의 객체배열의 형태로 변환해주는 것
		// 형식, 인코딩 지정해줘야함
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
