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

@WebServlet("/bDeleteAction.do")
public class _09_bDeleteAction extends HttpServlet {
	
	private static final long serialVersionUID = -1533061600059090528L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO bdto = new BoardDTO();
		bdto.setNum(Integer.parseInt(request.getParameter("num")));
		bdto.setPassword(request.getParameter("password"));
		
		boolean isDelete = BoardDAO.getInstance().deleteBoard(bdto);
		
		request.setAttribute("isDelete", isDelete);
		
		RequestDispatcher dis = request.getRequestDispatcher("_02_board/09_bDeletePro.jsp");
		dis.forward(request, response);
	}

}
