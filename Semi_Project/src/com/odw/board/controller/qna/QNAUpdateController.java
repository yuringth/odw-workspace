package com.odw.board.controller.qna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.QNABoardService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class QNAUpdateController
 */
@WebServlet("/update.qa")
public class QNAUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QNAUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post
		
		request.setCharacterEncoding("UTF-8");
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		String qnaBoardTitle = request.getParameter("title");
		String qnaBoardContent = request.getParameter("content");
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardTitle(qnaBoardTitle);
		b.setBoardContent(qnaBoardContent);
		
		int result = new QNABoardService().updateQNABoard(b);
		
		if(result > 0) {
			request.getSession().setAttribute("alerMsg", "수정에 성공 하셨습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.qa?bno=" + boardNo);
		} else {
			request.setAttribute("errorMsg", "수정에 실패하셨습니다.");
			request.getRequestDispatcher("views/common/error500.jsp").forward(request, response);
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
