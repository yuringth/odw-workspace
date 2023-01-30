package com.odw.board.controller.qna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.QNABoardService;
import com.odw.member.model.vo.Member;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class AjaxQNABoardInsertController
 */
@WebServlet("/rinsert.qa")
public class AjaxQNABoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxQNABoardInsertController() {
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
		
		int memNo =  ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
		
		Reply r = new Reply();
		r.setBoardNo(boardNo);
		r.setReplyContent(replyContent);
		r.setMemNo(memNo);
		
		int result = new QNABoardService().insertQNABoardReply(r);
		
		
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
