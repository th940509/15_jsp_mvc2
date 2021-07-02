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


@WebServlet("/bInfo.do")
public class _05_bInfo extends HttpServlet {
	
	private static final long serialVersionUID = -6043246087533916819L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO bdto =  BoardDAO.getInstance().getOneBoard(Integer.parseInt(request.getParameter("num")));
		request.setAttribute("bdto", bdto);
		
		RequestDispatcher dis = request.getRequestDispatcher("_02_board/05_bInfo.jsp");
		dis.forward(request, response);
	
	}

}
