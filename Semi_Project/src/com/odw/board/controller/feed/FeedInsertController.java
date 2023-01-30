package com.odw.board.controller.feed;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.FeedService;
import com.odw.board.model.vo.Board;
import com.odw.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class FeedInsertController
 */
@WebServlet("/insert.fe")
public class FeedInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 사진첨부, 아이디, 내용
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		
		// 2) 첨부파일 잘 들어갔는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// MultipartRequest객체생성
			// 1. 전송용량
			int maxSize = 1024 * 1024 * 100;
			
			// 2. 저장할 경로 제시
			String savePath = request.getServletContext().getRealPath("/resources/feed_upfiles/");
			
			// 파일명 수정해서 올리기
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 3) 값 뽑기
			String feedContent = multiRequest.getParameter("feedContent");
			int feedMemNo = Integer.parseInt(multiRequest.getParameter("feedMemNo"));
			String feedMemId = multiRequest.getParameter("feedMemId");
			String feedHash = multiRequest.getParameter("feedHash");
			
			// 4) board vo가공
			Board b = new Board();
			b.setBoardContent(feedContent);
			b.setMemNo(feedMemNo);
			b.setMemId(feedMemId);
			b.setKeyword(feedHash);
			
		
//			System.out.println(b);
			
			// 4) attachment 가공
			Attachment at = new Attachment();
			at.setOriginName(multiRequest.getOriginalFileName("feedFile")); // 원본명
			at.setChangeName(multiRequest.getFilesystemName("feedFile"));
			at.setFilePath("resources/feed_upfiles/"); // 파일저장 경로
			
//			System.out.println(at);
			
			// 5) 서비스단으로 넘기기
			int result = new FeedService().insertFeedBoard(b, at);
			
			// 6) 응답뷰
			if(result > 0) { //성공
				
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.fe");

			} else {
				request.getSession().setAttribute("alertMsg", "게시글 작성 실패");
				request.getRequestDispatcher("views/common/erroPage.jsp").forward(request, response);
				
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
