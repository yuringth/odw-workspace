package com.odw.admin.controller.communityMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.CommunityMenuService;

/**
 * Servlet implementation class DeleteNoticeListController
 */
@WebServlet("/deleteNoticeList.ad")
public class DeleteNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cpage = Integer.parseInt(request.getParameter("cpage"));
		String deleteYn = request.getParameter("deleteYn");
		String boardName = request.getParameter("boardName");
		
		
		switch(boardName) {
		case "자유게시판" : boardName = "free";
		break;
		case "리뷰게시판" : boardName = "review";
		break;
		case "피드게시판" : boardName = "feed";
		break;
		case "QNA게시판" : boardName = "qna";
		break;
		case "동행게시판" : boardName = "with";
		}
		
		
		
		String[] noticeNoList = request.getParameterValues("noticeNo");
		
		int result = new CommunityMenuService().deleteNoticeList(noticeNoList);

		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "공지글 숨기기 완료");
			response.sendRedirect(request.getContextPath() + "/notice.ad?cpage="+ cpage +"&boardName=" + boardName + "&deleteYn=" + deleteYn);

		} else {
			request.getSession().setAttribute("errorMsg", "공지글 숨기기 실패");
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
