package com.odw.admin.controller.infoMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.InfoMenuService;
import com.odw.attachment.model.vo.Attachment;
import com.odw.information.model.vo.Information;

/**
 * Servlet implementation class AdminLocalMountainUpdateFormController
 */
@WebServlet("/localUpdateForm.ad")
public class LocalMountainUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalMountainUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식 인코딩 X
		// 값 뽑아가자
		int infoNo = Integer.parseInt(request.getParameter("infoNo"));
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		String infoLocal = request.getParameter("infoLocal");
		//System.out.println(infoLocal);
		String deleteYn = request.getParameter("deleteYn");
		
		Information info = new InfoMenuService().selectInformation(infoNo);
		Attachment at = new InfoMenuService().selectAttachment(infoNo);
		
		request.setAttribute("info", info);
		request.setAttribute("at", at);
		request.setAttribute("infoLocal", infoLocal);
		request.setAttribute("cpage", cpage);
		request.setAttribute("deleteYn", deleteYn);
		
		request.getRequestDispatcher("views/admin/infoMenu/localMountainUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
