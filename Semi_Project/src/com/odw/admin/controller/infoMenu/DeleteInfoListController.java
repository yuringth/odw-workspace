package com.odw.admin.controller.infoMenu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.admin.model.service.InfoMenuService;

/**
 * Servlet implementation class DeleteInfoListController
 */
@WebServlet("/deleteInfoList.ad")
public class DeleteInfoListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInfoListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] infoNoList = request.getParameterValues("infoNo");
		String infoType = request.getParameter("infoType");
		String cpage = request.getParameter("cpage");
		String deleteYn = request.getParameter("deleteYn");
		String season = request.getParameter("season");
		String local = request.getParameter("local");
		
		int result = new InfoMenuService().deleteInfoList(infoNoList);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "정보글 숨기기 완료");
			
			
			
			
			
			
			switch(infoType) {
			case "local" :
				switch(local) {
				case "서울" : local = "S";
				break;
				case "인천" : local = "I";
				}
				response.sendRedirect(request.getContextPath() + "/local.ad?cpage=" + cpage + "&deleteYn=" + deleteYn +"&local=" + local);
			break;
			case "season" :
				switch(season) {
				case "봄" : season = "spring";
				break;
				case "여름" : season = "summer";
				break;
				case "가을" : season = "fall";
				break;
				case "겨울" : season = "winter";
				}
				response.sendRedirect(request.getContextPath() + "/season.ad?cpage=" + cpage + "&deleteYn=" + deleteYn +"&season=" + season);
			break;
			case "beginner" : 
				response.sendRedirect(request.getContextPath() + "/beginner.ad?cpage=" + cpage + "&deleteYn=" + deleteYn);
			}

		} else {
			request.getSession().setAttribute("errorMsg", "정보글 숨기기 실패");
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
