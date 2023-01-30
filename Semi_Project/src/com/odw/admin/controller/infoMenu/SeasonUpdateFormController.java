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
 * Servlet implementation class AdminSeasonUpdateFormController
 */
@WebServlet("/seasonUpdateForm.ad")
public class SeasonUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeasonUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int infoNo = Integer.parseInt(request.getParameter("infoNo"));
		
		Information info = new InfoMenuService().selectInformation(infoNo);
		Attachment at = new InfoMenuService().selectAttachment(infoNo);
		
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		String deleteYn = request.getParameter("deleteYn");
		String season = request.getParameter("season");
		
		
		request.setAttribute("cpage", cpage);
		request.setAttribute("deleteYn", deleteYn);
		request.setAttribute("season", season);
		request.setAttribute("info", info);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/admin/infoMenu/seasonUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
