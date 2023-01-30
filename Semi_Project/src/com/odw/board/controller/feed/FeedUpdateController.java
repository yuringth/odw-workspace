package com.odw.board.controller.feed;

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
import com.odw.board.model.service.FeedService;
import com.odw.board.model.vo.Board;
import com.odw.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class FeedUpdateController
 */
@WebServlet("/update.fe")
public class FeedUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 식별자 : 보드 넘버(내가 클릭한게 어떤 글인지), 파일 넘버(어떤 사진을 변경 할 것인지)
		
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기전에 multipart/form-data 전송방식으로 잘 전달이 되었는지 확인하기 (첨부파일과는 관계없음)
		if(ServletFileUpload.isMultipartContent(request)) { // "multipart/form-data" 이 전송방식이 잘 전달됐냐안됐냐 => 전달이 안되면 오타가 난 것이다
			
			// 파일 업로드 시 2가지 해야할일
			// 파일 용량 크기 지정
			int maxSize = 50 * 1024 * 1024;
				
			// 파일 저장경로 지정 (application으로 빼는 이유는 => 실제 작업은 컨트롤러에서 하고 있지만 실제 저장 경로는 webContent로 따로 있기때문에 application으로 빼준다) 
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/feed_upfiles");
			
			
			// 첨부파일이 있을 때 사용하는 객체 MultipartRequest
			// 전달된 파일명 수정 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// sql문이 몇개가 필요한지 생각해봐야한다 
			
			// 값을 뽑기때 게시글/첨부파일 2가지 해야함
			// 게시글 값 뽑기
			// 내가 게시글을 수정했을때 수정한 값을 뽑는 과정
			int feedBoardNo = Integer.parseInt(multiRequest.getParameter("feedBoardNo")); // 글번호(hidden) 식별자로 사용해야함
			String feedContent = multiRequest.getParameter("feedContent"); // 글내용
			String keyword = multiRequest.getParameter("feedHash"); // 해시태그
			
			// vo에 값 담기
			Board b = new Board();
			b.setBoardNo(feedBoardNo);
			b.setBoardContent(feedContent);
			b.setKeyword(keyword);
			
			
			// ( 새로운 파일명을 반환해주는 메소드 : .getOriginalFileName("key값")
			// 새로운 첨부파일이 존재한다면 객체에 담아야함.
			Attachment at = new Attachment();
			if(multiRequest.getOriginalFileName("feedFile") != null) { // 새로운 첨부파일이 있다
				at.setOriginName(multiRequest.getOriginalFileName("feedFile")); // 새 첨부파일의 원본파일명
				at.setChangeName(multiRequest.getFilesystemName("feedFile")); // 새 첨부파일의 수정파일명 
				at.setFilePath("resources/feed_upfiles/"); // 새 첨부파일의 파일경로
				
				at.setFileNo(Integer.parseInt(multiRequest.getParameter("fileNo")));
				
			
				// System.out.println(at);
				
				// 여기서 생각을 해봐야할것이, 새로운 첨부파일이 있지만 원본파일도 있었을 경우!!! => update
				// 게시글도 있고 + 파일 첨부도 있을 시 => update 사용 => 첨부파일의 번호를 식별자로 사용
				if(multiRequest.getParameter("originFileNo") != null) { // 기존의 첨부파일의 첨부파일 번호(식별자)가  있음
					
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo"))); // 기존파일이 있었을 경우, at테이블에 업데이트를 해야하니까, 기존파일의 파일넘버를 넘겼으니 그걸로 식별을 한다 
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
					
				} // else인 경우는 없다~ 나는 둘다 해야하기 때문이다.
			}
			
			
			// 서비스로 호출 (게시글도 있고, 첨부파일도 있을 시)
			int result = new FeedService().updateFeedBoard(b, at);
			
			// 응답화면지정 : 게시글 상세보기 화면으로 포워딩해줌
			if(result > 0) {
				
				request.setAttribute("alertMsg", "게시글 수정 성공");
				response.sendRedirect(request.getContextPath() + "/detail.fe?feedBoardNo=" + feedBoardNo);
			} else {
				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
