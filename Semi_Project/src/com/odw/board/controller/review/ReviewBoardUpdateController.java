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
 * Servlet implementation class ReviewBoardUpdateController
 */
@WebServlet("/update.re")
public class ReviewBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewBoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize =50 * 1024 * 1024;
			
			//파일저장 위치 정하기
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/review_upfiles");
		
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			// 게시글뽑아내기
			// 첨부파일, 글
			
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String boardContent = multiRequest.getParameter("content");
			String boardTitle = multiRequest.getParameter("title");
			
			
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardContent(boardContent);
			b.setBoardTitle(boardTitle);
			//객체 선언을 하는데, 첨부파일이 없으면 null 있으면 if문 안에서 객체 생성
			Attachment reAt = null;
			
			if(multiRequest.getOriginalFileName("reupfile") != null) {
				//새로운 업로드 파일이 있을 경우
				
				//파일이 있으니까 객체 생성
				reAt = new Attachment();
				
				//파일 첨부 과정과 동일하게 input의 name속성으로 값을 넣는다.
				reAt.setOriginName(multiRequest.getOriginalFileName("reupfile"));
				reAt.setChangeName(multiRequest.getFilesystemName("reupfile"));
				//파일 경로 지정
				reAt.setFilePath("resources/review_upfiles/");
				
				
				if(multiRequest.getParameter("originFileName") != null) {
					//새로운 업로드 파일이 있을 경우 + 기존의 업로드 파일이 있을 경우
					reAt.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					// 기존에 서버에 존재하던 첨부파일 삭제!
					new File(savePath + multiRequest.getParameter("originFileName")).delete();	
				} 
			}
			//서비스 요청
			int result = new ReviewBoardService().updateReviewBoard(b, reAt);
			
			
			if(result > 0) {
				
				request.setAttribute("alertMsg", "게시글 수정 성공입니다.");
				response.sendRedirect(request.getContextPath() + "/detail.re?bno=" + boardNo);
			} else {
				request.setAttribute("errorMsg", "게시글 작성 실패입니다.");
				request.getRequestDispatcher("views/common/errorPage").forward(request, response);
			}
			
//			////////////////////////////////////////////////
//			
//			String OriginName = multiRequest.getParameter("file");
//			String ChangeName = multiRequest.getParameter("file");
//			String UpdateSavePath = multiRequest.getParameter("file");
//			
//			Attachment reAt = null;
//			
//			if(multiRequest.getParameter("originFileNo") != null) {
//				
//				reAt.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
//				new File(savePath + multiRequest.getParameter("originFileName")).delete();	
//			} // 그면 if를 지워도 될거같은데..
//			//////////////////////////////////////////////////
//			
//			
//			if(multiRequest.getOriginalFileName("file") != null) {
//				
//				reAt = new Attachment();
//				
//				reAt.setOriginName("file");
//				reAt.setChangeName("file");
//				reAt.setFilePath("resources/review_upfiles/");
//				
//			}
//			
//			// 첨부파일 등록
//			int result = new ReviewBoardService().updateReviewBoard(b, reAt);
//			
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
