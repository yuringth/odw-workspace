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
 * Servlet implementation class QNABoardDetailController
 */
@WebServlet("/detail.qa")
public class QNABoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QNABoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 서비스 요청 - 조회수를 증가시키는 
		
		
		int result = new QNABoardService().increaseCount(boardNo);
		
		// 조회수가 성공적으로 증가하였다면 => board조회
		if(result > 0) {
			// board 조회
			Board b = new QNABoardService().selectQNABoard(boardNo);
			
			// 조회한 b 어트리뷰트에 담기
			request.setAttribute("b", b);
			// 화면 => 포워딩
			request.getRequestDispatcher("views/board/qna/qnaDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "확인해보기");
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
