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
 * Servlet implementation class QNAInsertController
 */
@WebServlet("/insert.qa")
public class QNAInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QNAInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post방식
		
		request.setCharacterEncoding("UTF-8");
		
		
		
		String QNABoardTitle = request.getParameter("title");
		String QNABoardContent = request.getParameter("content");
		int memNo = Integer.parseInt(request.getParameter("memNo")) ;
		
		
		Board b = new Board();
		b.setBoardTitle(QNABoardTitle);
		b.setBoardContent(QNABoardContent);
		b.setMemNo(memNo);
		
		
		
		int result = new QNABoardService().insertQNABoard(b);
		
		if(result > 0) {
		request.getSession().setAttribute("alertMsg", "게시글 작성 성공하셨습니다.");
		response.sendRedirect(request.getContextPath() + "/list.qa");
		} else {
			request.getSession().setAttribute("errorMsg", "게시글 작성에 실패하셨습니다.");
			response.sendRedirect("views/common/error500.jsp");
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
