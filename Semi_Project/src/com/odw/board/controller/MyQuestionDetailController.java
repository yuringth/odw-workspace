package com.odw.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.BoardService;
import com.odw.board.model.vo.Board;
import com.odw.board.model.vo.Qna;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class MyQuestionDetailController
 */
@WebServlet("/MyQuestionDetail.bo")
public class MyQuestionDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQuestionDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		int memNo = ((Member)session.getAttribute("loginUser")).getMemNo();
		Qna q = new Qna();
		 // 조회수 증가!
		 //System.out.println(result);
		 
		 // 조회수가 성공적으로 증가했다면, 게시글과 첨부파일이 있는지 조회할것
		 if(q != null) { // 조회 성공
			 
			 q = new BoardService().selectQna(memNo);
			 request.setAttribute("q", q);
			 
			 request.getRequestDispatcher("views/myPage/myQuestionDetailView.jsp").forward(request, response);
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
