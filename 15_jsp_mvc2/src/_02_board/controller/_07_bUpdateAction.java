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


@WebServlet("/bUpdateAction.do")
public class _07_bUpdateAction extends HttpServlet {

	private static final long serialVersionUID = 6861581419745627290L;
	
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
		bdto.setWriter(request.getParameter("writer"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setPassword(request.getParameter("password"));
		bdto.setContent(request.getParameter("content"));
		
		boolean isUpdate = BoardDAO.getInstance().updateBoard(bdto);
		request.setAttribute("isUpdate", isUpdate);
		
		RequestDispatcher dis = request.getRequestDispatcher("_02_board/07_bUpdatePro.jsp");
		dis.forward(request, response);
		
	}

}
