package com.odw.admin.controller.infoMenu;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.odw.admin.model.service.InfoMenuService;
import com.odw.attachment.model.vo.Attachment;
import com.odw.common.MyFileRenamePolicy;
import com.odw.information.model.vo.Information;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UpdateBeginnerController
 */
@WebServlet("/updateBeginner.ad")
public class UpdateBeginnerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBeginnerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식 인코딩 
		request.setCharacterEncoding("UTF-8");
		
		String deleteYn = "";
		int cpage = 0;
		
		// 값 뽑기 전에 첨부파일 잘 왔나 보자
		if(ServletFileUpload.isMultipartContent(request)) {
		int maxSize = 1024 * 1024 * 10;
		String savePath = request.getSession().getServletContext().getRealPath("/resources/info_upfiles/");
		
		// 파일 저장
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	
		// 값뽑기 - information
		int infoNo = Integer.parseInt(multiRequest.getParameter("infoNo"));
//			System.out.println(infoNo);
		String infoTitle = multiRequest.getParameter("title");
		
		String originFileNo = multiRequest.getParameter("originFileNo");
		String originFileName = multiRequest.getParameter("originFileName");
		deleteYn = multiRequest.getParameter("deleteYn");
		//System.out.println(deleteYn);
		cpage = Integer.parseInt(multiRequest.getParameter("cpage"));
		
		// 가공
		Information info = new Information();
		info.setInfoNo(infoNo);
		info.setInfoTitle(infoTitle);
		
		// Attachment 테이블에 insert할 정보 가공 필요
		Attachment at = null;
		
		// reUpFile이 있을 경우
		if(multiRequest.getOriginalFileName("reUpFile") != null) {
			
			
			at = new Attachment();
			
			at.setOriginName(multiRequest.getOriginalFileName("reUpFile"));		
			at.setChangeName(multiRequest.getFilesystemName("reUpFile"));
			at.setFilePath("resources/info_upfiles");
			
//				System.out.println(at.getOriginName());
//				System.out.println(at.getChangeName());
//				System.out.println(at.getFilePath());
			
			if(originFileNo != null) {
				// 기존 파일이 존재한다
				// 기존 파일이 가지고 있던 파일번호를 at에 담을 것
				at.setFileNo(Integer.parseInt(originFileNo));
				
				// 기존에 서버에 존재하던 첨부파일 삭제
				new File(savePath + originFileName).delete();
				// 어차피 update하는 데이터에 fileNo가 저장되어 있으니 굳이 update 필요하지 않음.
				// fileNo를 담지 않아도 됨
			} else {
				// 원래 첨부파일이 없었기 때문에 정보글 정보가 들어가야 한다.
				at.setInfoNo(infoNo);
			}

				// 나는 그냥 infoNo를 위에서 set해줘서 파일이 있었을 경우와 없었을 경우 모두 
				// set info_no = ?   할 것이다.
			}
			
			// reUpFile이 있든지 말든지 상관 없이 service단에 날릴 거니까 여기에서 보내는 것이 맞다!
			// case 1 : 새로운 첨부파일 X => b, null => boardUpdate만
			// case 2 : 새로운 첨부파일 O, 기존 첨부파일 O => boardUpdate, attachmentUpdate
			// case 3 : 새로운 첨부파일 O, 기존 첨부파일 O => boardUpdate, attachmentInsert
			int result = new InfoMenuService().updateBeginnerInfo(info, at, infoNo);
			
			
			
			if(result > 0) {
				// 1) 성공했으니 다시 리스트로 들어가고 alertMsg 보내주자
				request.getSession().setAttribute("alertMsg", "게시글 수정 완료");
				response.sendRedirect(request.getContextPath() + "/beginner.ad?cpage=" + cpage + "&deleteYn=" + deleteYn);
			} else {
				
				// 3) 파일이 없을 경우는 그냥 오류페이지로 보내주기
				request.getSession().setAttribute("errorMsg", "정보글 수정 실패");
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
