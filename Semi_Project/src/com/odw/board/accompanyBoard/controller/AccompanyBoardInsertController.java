package com.odw.board.accompanyBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.service.AccompanyBoardService;
import com.odw.board.model.vo.Board;
import com.odw.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class AccompanyBoardInsertController
 */
@WebServlet("/AccompanyBoardInsert.bo")
public class AccompanyBoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccompanyBoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//2첨부파일 
		if(ServletFileUpload.isMultipartContent(request)) { // 파일 전송이 잘 됐을 경우에 true
			
			//1 전송 용량 제한
			int maxSize = 1024 * 1024 * 10; // 1024 * 1024 = 1MB
			
			//2 저장할 서버의 물리적 경로 제시
			String savePath = request.getServletContext().getRealPath("/resources/accompany_upfiles/");
			
			//3 MultipartRequest 객체 만들기
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			//4 값 뽑기
			int memberCount = Integer.parseInt(multiRequest.getParameter("memberCount"));
			String dpDate = multiRequest.getParameter("dpDate");
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");
			String boardName = multiRequest.getParameter("boardName");
			int memNo = Integer.parseInt(multiRequest.getParameter("memNo"));
			String keyword = multiRequest.getParameter("keyword");
			
			// 5 가공
			Board b = new Board();
			
			b.setMemberCount(memberCount);
			b.setDpDate(dpDate);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setMemNo(memNo);
			b.setBoardName(boardName);
			b.setKeyword(keyword);
			
			
			Attachment at = null;
			
			// 사진이 하나니까 Attachment vo에 담기 key값은 input의 name
			String key = "file";
			
			if(multiRequest.getOriginalFileName(key) != null) {
				
				at = new Attachment();
				
				at.setOriginName(multiRequest.getOriginalFileName(key));
				at.setChangeName(multiRequest.getFilesystemName(key));
				at.setFilePath("resources/accompany_upfiles/");
			}
			
			// 6 서비스 요청 
			int result = new AccompanyBoardService().insertAccompanyBoard(b, at);
			
			if(result > 0) {
				 request.getSession().setAttribute("alertMsg", "게시글이 등록되었씁니다.");
				 response.sendRedirect(request.getContextPath() + "/accompanyBoardList.bo");
				 
			} else {
				System.out.println("실패 ㅋㅋ");
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
