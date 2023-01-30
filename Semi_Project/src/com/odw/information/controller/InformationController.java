package com.odw.information.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odw.information.model.service.InformationService;
import com.odw.information.model.vo.Information;

/**
 * Servlet implementation class InformationController
 */
@WebServlet("/information.ma")
public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//화면 띄우기 전 테이블에서 조회해오기
		
		// 조회 결과 여러개
		ArrayList<Information> list = new InformationService().selectInformation();
		
		// 1. 산 정보
		// 2. 초보자 길잡이
		// 3. 계절별 산
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("views/mainpage/informationView.jsp").forward(request, response);
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
