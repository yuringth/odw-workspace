package com.odw.admin.controller.communityMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.CommunityMenuService;
import com.odw.attachment.model.vo.Attachment;
import com.odw.notice.model.vo.Notice;

/**
 * Servlet implementation class AdminNoticeUpdateFormController
 */
@WebServlet("/noticeUpdateForm.ad")
public class NoticeUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		String deleteYn = request.getParameter("deleteYn");
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		String boardName = request.getParameter("boardName");
		
		Notice notice = new CommunityMenuService().selectNotice(noticeNo);
		Attachment at = new CommunityMenuService().selectAttachment(noticeNo);
		
		request.setAttribute("notice", notice);
		request.setAttribute("at", at);
		request.setAttribute("deleteYn", deleteYn);
		request.setAttribute("cpage", cpage);
		request.setAttribute("boardName", boardName);
		
		request.getRequestDispatcher("views/admin/communityMenu/noticeUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
