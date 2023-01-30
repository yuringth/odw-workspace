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
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class MyBoardDetailViewController
 */
@WebServlet("/myBoardDetail.bo")
public class MyBoardDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardDetailViewController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		int memNo = ((Member)session.getAttribute("loginUser")).getMemNo();
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardService bService = new BoardService();
		 // 조회수 증가!
		 int result = bService.increaseCount(bno);
		 //System.out.println(result);
		 
		 // 조회수가 성공적으로 증가했다면, 게시글과 첨부파일이 있는지 조회할것
		 if(result > 0) { // 조회 성공
			 
			 Board b = bService.selectBoard(bno, memNo);
			 Attachment at = bService.selectAttachment(bno);
			 
			 request.setAttribute("b", b);
			 request.setAttribute("at", at);
			 
			 request.getRequestDispatcher("views/myBoard/myBoardDetailView.jsp").forward(request, response);
		 }else { // 조회 실패
			 request.setAttribute("errorMsg", "조회실패");
			 request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
