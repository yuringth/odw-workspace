package com.odw.board.controller.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.FreeService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class FreeUpdateFormController
 */
@WebServlet("/updateFrom.fr")
public class FreeUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 get방식
		
		// 2) 값뽑기 (글번호, 말머리, 제목, 내용)
		
		// 해당 글 번호 뽑은 뒤 -> 일치하는 board테이블, attachment테이블 조회
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	
		//System.out.println(boardNo);
		
		Board b = new FreeService().selectFreeBoard(boardNo);
		Attachment at = new FreeService().selectFreeAttachment(boardNo);
		
		// 3) 조회한 값 담기
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		
		// 4) 응답화면 지정
		request.getRequestDispatcher("views/board/free/freeUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
