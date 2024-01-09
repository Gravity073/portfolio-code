package board.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;

@SuppressWarnings("serial")
@WebServlet("/addDummyPost.do")
public class _05_addDummyPost extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO.getInstance().addDummyPost();
		
		System.out.println("더미 추가 완료");
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/05_addDummyPost.jsp");
		dis.forward(request, response);
	}

}
