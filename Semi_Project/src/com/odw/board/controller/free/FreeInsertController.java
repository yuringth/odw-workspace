package com.odw.board.controller.free;

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
import com.odw.board.model.service.FreeService;
import com.odw.board.model.vo.Board;
import com.odw.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class FreeInsertController
 */
@WebServlet("/insert.fr")
public class FreeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인코딩 POST
		request.setCharacterEncoding("UTF-8"); // getCharaterEncoding 아님
		// 클래스 = 사용자정의자료형
		// 2) 값뽑기 => 파일첨부
		
		if(ServletFileUpload.isMultipartContent(request)) {
			// enctype타입을 사용했을땐 파일이 첨부 되었는지 안되었는지 확인작업을 꼭 해줘야함
			
			// 전반적인 내용들이 수정되어 잘 전송되었을 경우 => true / 아니면 => false
			// System.out.println("잘나오네");
			
			// 사진을 업로드하면 resources에 저장되며, 파일 돌려달라고 하면 webContent에있는 것을 돌려준다
			// 스텝1) 전송되는 파일을 처리할 작업
			// 1. 전송되는 파일 크기의 용량제한
			int maxSize = 50 * 1024 * 1024; // 50MByte
			
			// 2. 전달된 파일을 저장할 경로
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/free_upfiles/");
			
//			System.out.println(maxSize);
//			System.out.println(savePath);
			
			
			// 스텝2) 서버에 업로드 작업(파일명 수정)
			// request를 multiRequest로 바꿔서 사용하자
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// new MyFileRenamePolicy() 객체가 생성이 되면 rename이라는 메소드가 실행되어 파일명이 변경된다.
			
			
			// 2. 값 뽑기 
			// 글번호, 회원번호, 게시판이름, 말머리, 제목, 내용
			// BOARD_NO, MEM_NO, BOARD_NAME, BOARD_CATEGORY, BOARD_TITLE, BOARD_CONTENT  
			String memNo = multiRequest.getParameter("memNo");
			String boardName = multiRequest.getParameter("boardName");
			String boardCategory = multiRequest.getParameter("boardCategory");
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");
			
			// VO 객체로 가공 => 첫번째 insert문(board)
			Board b = new Board();
			b.setMemNo(Integer.parseInt(memNo));
			b.setBoardName(boardName);
			b.setBoardCategory(boardCategory);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			
			// 두번째 insert문(attachment) 있을수도 있고, 없을수도 있으니, null로 초기화
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("upfile") != null) { // 원본파일명이 null이 아니면, 첨부파일이 있다는 의미
				
				// 첨부파일이 있으니 => vo 가공
				at = new Attachment();
				
				// BOARD_NO, FILE_NO => vo객체에 넣지 않는 이유는 시퀀스는 오라클에서 사용하는 것이며, vo객체에는 없기 때문
				// 원본파일명
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				// 수정파일명
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				// 파일경로
//				/ 수정함
				at.setFilePath("resources/free_upfiles");
				
			}
			
			// 4) 서비스로 요청
			int result = new FreeService().insertFreeBoard(b, at);
				
			// 5) 응답페이지
			if(result > 0) {
				
				request.getSession().setAttribute("alertMsg", "게시글작성성공");
				response.sendRedirect(request.getContextPath() + "/list.fr?cpage=1");
				
			} else {
				
				if(at != null) {
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.getSession().setAttribute("alertMsg", "게시글 작성 실패");
			
				request.getRequestDispatcher("views/common/errorPage").forward(request, response);
				
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
