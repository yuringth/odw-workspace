package com.odw.board.accompanyBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.AccompanyBoardService;
import com.odw.member.model.vo.Member;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class AjaxAccompanyBoardReplyinsert
 */
@WebServlet("/accompanyBoardRinsert.bo")
public class AjaxAccompanyBoardReplyinsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxAccompanyBoardReplyinsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		String replyContent = request.getParameter("content");
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		//가공
		Reply r = new Reply();
		r.setBoardNo(boardNo);
		r.setMemNo(memNo);
		r.setReplyContent(replyContent);
		
		//서비스
		int result = new AccompanyBoardService().insertAccompanyBoardReply(r);
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
