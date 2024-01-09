package board.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardDTO;

@SuppressWarnings("serial")
@WebServlet("/updatePost.do")
public class _07_updatePost extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("num", num);
		
		BoardDTO post = BoardDAO.getInstance().getPost(num);
		request.setAttribute("post", post);
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/07_updatePost.jsp");
		dis.forward(request, response);
	}

}
