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
 * Servlet implementation class FreeUpdateController
 */
@WebServlet("/update.fr")
public class FreeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FreeUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 post
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값뽑기 전 파일이 전송되었는지 안되었는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드
			// 1. 전송파일 용량 제한
			int maxSize = 50 * 1024 * 1024; // 50MByte
			
			// 2. 전달된 파일을 저장시킬 폴더 지정 savaPath
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/free_upfiles");

			// 전달된 파일명 수정 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
			// board 업로드 => 게시글은 무조건 수정함(공통)
			// multiRequest에서 값뽑기 
			int boardNo = Integer.parseInt(multiRequest.getParameter("boardNo")); // 글번호
			String boardCategory = multiRequest.getParameter("category"); // 말머리
			String boardTitle = multiRequest.getParameter("title"); // 제목
			String boardContent = multiRequest.getParameter("content"); // 내용
			
			// vo에 가공
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardCategory(boardCategory);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			// ATTACHMENT는 게시글안에 있을수도 있고 없을수도 있으니 객체만 선언하기
			Attachment at = null;
			
			// 첨부파일 있는지 먼저 확인 ( 새로운 파일명을 반환해주는 메소드 : .getOriginalFileName("key값") )
			if(multiRequest.getOriginalFileName("reUpfile") != null) { // 첨부파일이 있을경우
				
				// 새로운 첨부파일이 존재한다면 객체 생성 후, 원본이름, 수정된이름, 파일경로 객체에 담기
				
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile")); // 실제 파일이름
				at.setChangeName(multiRequest.getFilesystemName("reUpfile")); // 업로드될 파일 이름
				at.setFilePath("resources/free_upfiles");
				
				// 올릴 첨부파일도 있고 + 기존에 올려둔 첨부파일도 있을 경우
				if(multiRequest.getParameter("originFileNo") != null) { // 기존파일이 존재한다면
					
					// 기존 파일이 가지고있던 파일번호를 at에 담기
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					// 기존에 존재했던 첨부파일 삭제
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
				} else {
					// 올릴 첨부파일은 있는데 + 기존에 파일이 없었을 경우
					// 어떤 게시글의 첨부파일인지 boardNo
					at.setBoardNo(boardNo);
				}
			}
			
			// 서비스에 요청
			// 새로운 첨부파일 없고, 게시글만 수정할 경우 => update board
			// 새로운 첨부파일 있고, 기존에 첨부파일 있을 경우 => update board, update attachment
			// 새로운 첨부파일 있고, 기존에 첨부파일 없을경우 => update board, insert attachment
			
			int result = new FreeService().updateFreeBoard(b, at);
			
			
			// 응답화면 지정
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "게시글 수정 성공");
				response.sendRedirect(request.getContextPath() + "/detail.fr?bno=" + boardNo);
			} else {
				request.getSession().setAttribute("alertMsg", "게시글 수정 실패");
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
