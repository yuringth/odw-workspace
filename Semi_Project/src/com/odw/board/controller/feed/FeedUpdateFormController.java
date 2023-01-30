package com.odw.board.controller.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.FeedService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class FeedUpdateFormController
 */
@WebServlet("/updateForm.fe")
public class FeedUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get 인코딩=> x 
		
		// 값뽑기
		int feedBoardNo = Integer.parseInt(request.getParameter("feedBoardNo"));
		
//		System.out.println(feedBoardNo);
		// vo에 가공 => x
		
		// 서비스단으로 넘기기
		// board
		Board b = new FeedService().selectFeedFormBoard(feedBoardNo);
		
//		System.out.println(b);
		
		// attachment
		Attachment at = new FeedService().selectFeedAttachment(feedBoardNo);
		
//		System.out.println(at);
		
		// 응답화면지정
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/board/feed/feedUpdate.jsp").forward(request, response);
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
