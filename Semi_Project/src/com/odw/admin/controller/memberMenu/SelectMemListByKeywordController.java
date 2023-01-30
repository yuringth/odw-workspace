package com.odw.admin.controller.memberMenu;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.admin.model.service.MemberMenuService;
import com.odw.common.model.vo.PageInfo;
import com.odw.member.model.vo.Member;

/**
 * Servlet implementation class AjaxSelectMemListByKeywordController
 */
@WebServlet("/selectMem.ad")
public class SelectMemListByKeywordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMemListByKeywordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) post 방식 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기

		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		
		// 3) 가공
		
		ArrayList<Member> list = new MemberMenuService().selectMemListByKeyword(keyword, condition);
		
		response.setContentType("application/json; charset=UTF-8");
	
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
