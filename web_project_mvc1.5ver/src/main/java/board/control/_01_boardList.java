package board.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardDTO;

@SuppressWarnings("serial")
@WebServlet("/boardList.do")
public class _01_boardList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 게시글 수
		int allPost = BoardDAO.getInstance().allPostNum();
		// 한 페이지 게시글 수
		int onePagePost = 5;
		// (현재 페이지에서)시작 게시글
		int startPost = 1;
		if(request.getParameter("startPost")!=null) {
			startPost = Integer.parseInt(request.getParameter("startPost"));
		}
		// (현재 페이지에서)끝 게시글
		int endPost = startPost + onePagePost - 1;
		if(endPost > allPost) {
			endPost = allPost;
		}
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("전체 게시글 수 : " + allPost);
		System.out.println("한 페이지 게시글 수 : " + onePagePost);
		System.out.println("(현재 페이지에서)시작 게시글 : " + startPost);
		System.out.println("(현재 페이지에서)끝 게시글 : " + endPost);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@");
		
		request.setAttribute("allPost", allPost);
		request.setAttribute("onePagePost", onePagePost);
		request.setAttribute("startPost", startPost);
		request.setAttribute("endPost", endPost);

		// 전체 페이지 수
		int allPage = allPost / onePagePost;
		if(allPost % onePagePost != 0) {
			allPage += 1;
		}
		// 화면에 보여줄 페이지 번호 수 
		int frontPage = 3;
		// 시작 페이지 번호
		int startPageNum = 1;
		if(request.getParameter("startPageNum")!=null) {
			startPageNum = Integer.parseInt(request.getParameter("startPageNum"));
		}
		// 끝 페이지 번호
		int endPageNum = startPageNum + frontPage - 1;
		if(endPageNum > allPage) {
			endPageNum = allPage;
		}
		
		System.out.println("전체 페이지 수 : " + allPage);
		System.out.println("화면에 보여줄 페이지 번호 수 : " + frontPage);
		System.out.println("시작 페이지 번호 : " + startPageNum);
		System.out.println("끝 페이지 번호 : " + endPageNum);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@");
		
		request.setAttribute("allPage", allPage);
		request.setAttribute("frontPage", frontPage);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		
		ArrayList<BoardDTO> post = BoardDAO.getInstance().getPostList(startPost, endPost);
		request.setAttribute("post", post);
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/01_boardList.jsp");
		dis.forward(request, response);
	}

}
