package com.odw.board.controller.review;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.ReviewBoardService;
import com.odw.board.model.vo.Board;
import com.odw.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/insert.re")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		if(ServletFileUpload.isMultipartContent(request)) {
		
			int maxSize = 1024 * 1024 * 10;		
			 
			String savePath =  request.getServletContext().getRealPath("/resources/review_upfiles/");
		
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
								     					// 파일첨부시 지정경로에 저장            객체        파일을저장할 물리적인 경로	  	           이름바꾸기 객체를 생성
			
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
//			String boardCategory = multiRequest.getParameter("category");
			int memNo = Integer.parseInt(multiRequest.getParameter("memNo"));
			
			
			
			Board b = new Board();
			
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
//			b.setBoardCategory(boardCategory);
			b.setMemNo(memNo);
			
			
			
			Attachment reAt = null;
			// => 첨부파일이 있으면 "원본파일명" / 첨부파일이 없으면 null 리턴 board부분
			String key = "file"; //키 값은 input type="file" 의 name 속성
			
			if(multiRequest.getOriginalFileName(key) != null) { //파일이 있을 때
			reAt = new Attachment();
			reAt.setOriginName(multiRequest.getOriginalFileName(key));// 원본 파일
			// 수정파일
			reAt.setChangeName(multiRequest.getFilesystemName(key));
			// 파일 경로
			reAt.setFilePath("resources/review_upfiles/");
			}
			
				// 요철
			int result = new ReviewBoardService().insertReviewBoard(b, reAt);

			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "게시글작성 성공~");
				response.sendRedirect(request.getContextPath() + "/list.re?cpage=1");

			} else { // 실패 => 에러페이지
				
				request.setAttribute("errorMsg", "등록 실패");
				request.getRequestDispatcher("views/common/error500.jsp").forward(request, response);
			}
	
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
