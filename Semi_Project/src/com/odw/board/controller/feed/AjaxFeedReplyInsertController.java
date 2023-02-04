package com.odw.board.controller.feed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.odw.board.model.service.FeedService;
import com.odw.member.model.vo.Member;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class AjaxFeedReplyInsertController
 */
// 댓글작성
@WebServlet("/rinsert.fe")
public class AjaxFeedReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxFeedReplyInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 post 
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String replyContent = request.getParameter("replyContent");
		
////////////////////////////////////////////////

//		HttpSession session = request.getSession();
//		Member loginUser = (Member)session.getAttribute("loginUser");
//		int memNo = loginUser.getMemNo();
		
		// 위 아래 같은 코드임
		
//		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();

////////////////////////////////////////		
		
		
		// 3) 값 담기
		Reply r = new Reply();
		r.setBoardNo(boardNo);
		r.setMemNo(memNo);
		//r.setMemId(memId);
		r.setReplyContent(replyContent);
		
		// 4) 서비스단으로 넘겨주기
		int result = new FeedService().insertReply(r);
		
		// 5) 응답화면 지정
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(result, response.getWriter());
		
		/*
		response.setContentType("application/json; chartType:UTF-8");
		new Gson().toJson().
		*/
		
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
