package com.odw.board.controller.free;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.odw.board.model.service.FeedService;
import com.odw.board.model.service.FreeService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class AjaxFreePreviewController
 */
@WebServlet("/previewboard.fr")
public class AjaxFreePreviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxFreePreviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Board> list = new FreeService().selectFreePreview();

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
