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
@WebServlet("/allDelete.do")
public class _06_allDelete extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO.getInstance().allDelete();
		
		System.out.println("글 전체 삭제");
		
		RequestDispatcher dis = request.getRequestDispatcher("/board/06_allDelete.jsp");
		dis.forward(request, response);
	}

}


