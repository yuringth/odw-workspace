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
 * Servlet implementation class myLikeListDetailController
 */
@WebServlet("/myLikeListDetail.bo")
public class myLikeListDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myLikeListDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int bno = Integer.parseInt(request.getParameter("bno"));
		HttpSession session = request.getSession();
		int memNo = ((Member)session.getAttribute("loginUser")).getMemNo();
		
		BoardService lService = new BoardService();
		
		
		int result = lService.increaseCount(bno);
		
		if(result >0) {
			Board b = lService.selectBoard(bno, memNo);
			Attachment at = lService.selectAttachment(bno);
			
			request.setAttribute("b", b);
			request.setAttribute("at", at);
		
			request.getRequestDispatcher("views/myBoard/myLikeListDetailView.jsp").forward(request, response);
		
		}else {
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
