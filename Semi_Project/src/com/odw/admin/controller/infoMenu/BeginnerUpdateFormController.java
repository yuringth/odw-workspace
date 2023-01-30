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
 * Servlet implementation class AdminBeginnerUpdateFormController
 */
@WebServlet("/beginnerUpdateForm.ad")
public class BeginnerUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeginnerUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int infoNo = Integer.parseInt(request.getParameter("infoNo"));
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		String deleteYn = request.getParameter("deleteYn");
		//System.out.println(deleteYn);
		
		Information info = new InfoMenuService().selectInformation(infoNo);
		Attachment at = new InfoMenuService().selectAttachment(infoNo);
		
		request.setAttribute("deleteYn", deleteYn);
		request.setAttribute("info", info);
		request.setAttribute("at", at);
		request.setAttribute("cpage", cpage);
		request.setAttribute("cpage", cpage);
		
		
		request.getRequestDispatcher("views/admin/infoMenu/beginnerUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
