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
@WebServlet("/deletePro.do")
public class _10_deletePro extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String check = request.getParameter("check");
		String num = request.getParameter("num");
		int judg = -1;
		if(check.equals("확인")) {
			BoardDAO.getInstance().deletePost(num);
			judg = 1;
		}
		
		request.setAttribute("judg", judg );
			
		RequestDispatcher dis = request.getRequestDispatcher("/board/10_deletePro.jsp");
		dis.forward(request, response);
	}

}
