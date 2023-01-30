package com.odw.board.controller.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.odw.attachment.model.vo.Attachment;
import com.odw.board.model.dao.FreeDao;
import com.odw.board.model.service.FreeService;
import com.odw.board.model.vo.Board;

/**
 * Servlet implementation class FreeDetailFormController
 */
@WebServlet("/detail.fr")
public class FreeDetailFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeDetailFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		// bno를 넘겨줬으니까 받아야한다? key=value 값으로 넘어줬으니까 get을 사용한다
		// 1) get방식 => 인코딩 x
		
		// 2) 값 뽑기
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 3) vo에 담기 => 값뽑은게 1개니까 안담는다
		
		// 4) 서비스단으로 넘기기
		
		// 4_0 조회수 int => boardNo를 식별자로 사용한다. 
		int result = new FreeService().increaseCount(boardNo);
		
		if(result > 0) {
			// 4_1) 게시판
			Board b = new FreeService().detailFreeBoard(boardNo);
			
			// 4_2) 첨부파일
			Attachment at = new FreeService().detailAttachmentBoard(boardNo);
			
			
			// 5) 응답화면 지정
//			여기 수정했는데 혹시몰라 나중에 돌려주자 => session으로 수정함
			HttpSession session = request.getSession();
			
			session.setAttribute("b", b);
			session.setAttribute("at", at);
			
			// session.setAttribute("alertMsg", "게시글 조회 성공");
			request.getRequestDispatcher("/views/board/free/freeDetailView.jsp").forward(request, response);
	
		} else {
			
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
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
