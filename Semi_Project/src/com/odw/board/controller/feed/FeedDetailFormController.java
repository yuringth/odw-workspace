package com.odw.board.controller.feed;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.FeedService;
import com.odw.board.model.vo.Board;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class FeedDetailFormController
 */
@WebServlet("/detail.fe")
public class FeedDetailFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedDetailFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 여기서 db에 있는 값을 가져와서 포워딩으로 넘겨준다~
		// 근데 가져와야하는게, board / attachment가잇다 => 두개를 가져와야한다...
		// key = value / feedBoardNo = <%= b.getBoardNo() %>
		
		// 인코딩 => get방식 
		
		// 값뽑기
		int feedBoardNo = Integer.parseInt(request.getParameter("feedBoardNo"));
		
//		System.out.println(feedBoardNo);
		
		// 값 담기 => x
		
		// 서비스 호출
		// 1) 게시판
		Board b = new FeedService().detailFeedBoard(feedBoardNo);
		
		// 2) 첨부
		Attachment at = new FeedService().detailFeedAttachment(feedBoardNo);
		
		//3) 
		ArrayList<Reply> list = new FeedService().detailFeedReply(feedBoardNo);
		//System.out.println(list);
		
		// 응답화면 지정
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		// 게시글 번호를 식별자로 사용하여 댓글첨부(오류뜨면 삭제하기)
		request.setAttribute("list", list);
		
		
		request.getRequestDispatcher("views/board/feed/feedDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
