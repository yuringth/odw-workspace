package com.odw.board.controller.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.FreeService;
import com.odw.member.model.vo.Member;
import com.odw.reply.model.vo.Reply;

/**
 * Servlet implementation class AjaxFreeReplyInsertController
 */
@WebServlet("/rinsert.fr")
public class AjaxFreeReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFreeReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// post 인코딩
		request.setCharacterEncoding("UTF-8");
		
		
		// 값뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String replyContent = request.getParameter("replyContent");
		
//		System.out.println(boardNo);
		
		
		// 로그인한 회원정보
		int memNo = ((Member)request.getSession().getAttribute("loginUser")).getMemNo();
		
//		System.out.println(memNo);
		
		// vo객체로 가공 => reply
		Reply r = new Reply();
		r.setBoardNo(boardNo);
		r.setReplyContent(replyContent);
		r.setMemNo(memNo); 
		
		//서비스 호출
		int result = new FreeService().insertReply(r);
		
		System.out.println(result);
		
		// 응답 => ajax가 할것임~
		// Gson, Json => 넘겨야할 값이 여러개일때 묶을때(vo, 객체배열, 등등)
		// => result는 0 아님 1임
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
