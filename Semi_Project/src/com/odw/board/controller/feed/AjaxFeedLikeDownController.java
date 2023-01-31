package com.odw.board.controller.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.board.model.service.FeedService;
import com.odw.board.model.vo.Board;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class AjaxFeedLikeDownController
 */


//버튼을 눌렀을때 어떤 게시글에 누가 눌렀는지 insert
@WebServlet("/downLike.fe")
public class AjaxFeedLikeDownController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxFeedLikeDownController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		//int result = new FeedService().deleteLike(boardNo, memNo);
		Board b = new FeedService().downLike(boardNo, memNo);
		
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
