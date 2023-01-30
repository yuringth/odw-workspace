package com.odw.board.controller.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.ReviewBoardService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class ReviewBoardDetailController
 */
@WebServlet("/detail.re")
public class ReviewBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewBoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		
		int result = new ReviewBoardService().increaseCount(boardNo);
		
		
		if(result > 0) {
			Board b = new ReviewBoardService().selectReviewBoardDetail(boardNo);
			
			
			
			Attachment reAt = new ReviewBoardService().selectReviewBoardDetailAttachment(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("reAt", reAt);
			
			
			
			request.getRequestDispatcher("views/board/review/reviewDetailView.jsp").forward(request, response);
			
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
