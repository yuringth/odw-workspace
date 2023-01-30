package com.odw.board.controller.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.FeedService;

/**
 * Servlet implementation class FeedDeleteReplyController
 */
@WebServlet("/deleteReply.fe")
public class FeedDeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedDeleteReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get방식
		
		// 값뽑기
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		int feedBoardNo = Integer.parseInt(request.getParameter("feedBoardNo"));
//		System.out.println("ㅎㅇㅎㅇ" + replyNo);
//		System.out.println("보드넘버ㅎㅇ" + feedBoardNo);
		// 값담기ㄴㄴ
		
		// 서비스로 값을 넘기기
		int result = new FeedService().deleteReply(replyNo);
		
//		System.out.println("기달" + result);
		
		// 응답화면 보내기
		if(result > 0) { // 삭제 성공
			
			request.setAttribute("alertMsg", "댓글 수정 성공");
			response.sendRedirect(request.getContextPath() + "/detail.fe?feedBoardNo=" + feedBoardNo);
//			response.sendRedirect(request.getContextPath() + "/list.fe");
			
//			request.getRequestDispatcher("views/board/feed/feedDetailView.jsp").forward(request, response);
		
		} else { // 삭제실패
			
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
