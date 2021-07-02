package _02_board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _02_board.dao.BoardDAO;
import _02_board.dto.BoardDTO;


@WebServlet("/bUpdate.do")
public class _06_bUpdate extends HttpServlet {
	
	private static final long serialVersionUID = -7646685604826017421L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO mdto = BoardDAO.getInstance().getOneUpdateBoard(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("mdto", mdto);
		
		RequestDispatcher dis = request.getRequestDispatcher("_02_board/06_bUpdate.jsp");
		dis.forward(request, response);
		
	}

}
