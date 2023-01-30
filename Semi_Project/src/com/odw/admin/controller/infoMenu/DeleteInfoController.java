package com.odw.admin.controller.infoMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.InfoMenuService;

/**
 * Servlet implementation class DeleteInfoController
 */
@WebServlet("/deleteInfo.ad")
public class DeleteInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 죄송합니다. 사실 이 기능은 정보글을 삭제하는 것이 아니라
		// delete_yn이 y일 경우 n으로, n일 경우 y로 바꿔주는 기능입니다.
		// updateInfoStatus라고 이름짓는 게 더 맞는데 이미 너무 많이 온 후에 깨달았어요...
		
		
		int infoNo = Integer.parseInt(request.getParameter("infoNo"));
		String infoType = request.getParameter("infoType");
		
		int result = new InfoMenuService().deleteInfo(infoNo);
		
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().print(result);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
