package com.odw.admin.controller.communityMenu;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.odw.admin.model.service.CommunityMenuService;
import com.odw.attachment.model.vo.Attachment;
import com.odw.common.MyFileRenamePolicy;
import com.odw.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class InsertNotcieController
 */
@WebServlet("/insertNotice.ad")
public class InsertNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) post 방식으로 넘겼으니 인코딩
				request.setCharacterEncoding("UTF-8");
				
				// 2_1) 값 뽑기 전에 첨부파일을 서버에 올리도록 하자
				// form 태그로 전송하는데 일반방식이 아니라 multipart/form-data 방식으로 전송했는지 확인
				if(ServletFileUpload.isMultipartContent(request)) {
					// (1_1) 용량 제한
					int maxSize = 1024 * 1024 * 10; // 10mb
					
					// (1_2) 경로를 제한하자
					// 경로는 절대 경로로 구해야 되는데, 이를 구하려면 application을 만들어서 getRealPath() 메소드를 활용해야 함
					// request.getSession().getServletContext();이걸로 application을 만들자
					String savePath = request.getSession().getServletContext().getRealPath("/resources/notice_upfiles/");
					
					// (2) MultipartRequest객체를 생성해서 지정한 경로에 날려주자!
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					// 이거 날리면 바로 지정 경로 즉 서버에 파일 올라가고, MyFileRenamePolicy는 FileRenamePolicy의 자식 클래스라 들어갈 수 있음.
					
					// 2_2) 값을 뽑아보자
					int memNo = Integer.parseInt(multiRequest.getParameter("memNo"));
					String boardName = multiRequest.getParameter("boardName");
					String noticeTitle = multiRequest.getParameter("noticeTitle");
					String noticeContent = multiRequest.getParameter("noticeContent");
					
					// 3) VO 가공하자
					// information 테이블에 insert할 정보 가공
					Notice notice = new Notice();
					notice.setMemNo(memNo);
					notice.setBoardName(boardName);
					notice.setNoticeTitle(noticeTitle);
					notice.setNoticeContent(noticeContent);

					// Attachment 테이블에 insert할 정보  가공해야 되는데, 첨부파일을 안 올릴 수도 있다.
					Attachment at = null;
					// 첨부파일이 있다면 multiRequest.getOriginalFileName("키값") 메소드 사용했을 때
					// 원본파일명이 반환되고, 없으면 null값이 반환된다.
					
					if(multiRequest.getOriginalFileName("upFile") != null) {
						at = new Attachment();
						
						at.setOriginName(multiRequest.getOriginalFileName("upFile"));
						
						at.setChangeName(multiRequest.getFilesystemName("upFile"));
						
						at.setFilePath("resources/notice_upfiles");
					}
					
					// 4) 드디어 Service단으로 토스!
					
					int result = new CommunityMenuService().insertNotice(notice, at);
					
					// 5) 화면지정
					if(result > 0) {
						// 성공했으니 다시 리스트로 들어가고 alertMsg를 보내주자
						request.getSession().setAttribute("alertMsg", "정보글 등록 완료");
						response.sendRedirect(request.getContextPath() + "/notice.ad?cpage=1&boardName=all&deleteYn=all");
					} else {
						// 1) 파일이 있을 경우는 파일을 삭제해주자
						if(at != null) {
							new File(savePath + at.getChangeName()).delete();
						}
						// 2) 파일이 없을 경우는 그냥 오류 페이지로 보내주자
						request.getSession().setAttribute("errorMsg", "정보글 등록 실패");
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
