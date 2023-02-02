package com.odw.board.controller.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.board.model.service.FreeService;

/**
 * Servlet implementation class FreeDeleteReplyController
 */
@WebServlet("/deleteReply.fr")
public class FreeDeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FreeDeleteReplyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get방식
		
		// 값뽑기
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		int freeBoardNo = Integer.parseInt(request.getParameter("bno"));
		
		int result = new FreeService().deleteReply(replyNo);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "댓글 삭제 성공");
			response.sendRedirect(request.getContextPath() + "/detail.fr?bno=" + freeBoardNo);
		} else {
			request.getSession().setAttribute("alertMsg", "댓글 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage").forward(request, response);
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
