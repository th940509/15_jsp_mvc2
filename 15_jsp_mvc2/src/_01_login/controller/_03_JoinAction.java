package _01_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _01_login.dao.MemberDAO;
import _01_login.dto.MemberDTO;


@WebServlet("/joinAction.do")
public class _03_JoinAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	public void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String id    = request.getParameter("id");
		String pw    = request.getParameter("pw");
		String name  = request.getParameter("name");
		String tel   = request.getParameter("tel");
		String email = request.getParameter("email");
		
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);
		mdto.setPw(pw);
		mdto.setName(name);
		mdto.setTel(tel);
		mdto.setEmail(email);
		
		boolean isJoin = MemberDAO.getInstance().joinMember(mdto);
		
		request.setAttribute("isJoin", isJoin);
		
		RequestDispatcher dis = request.getRequestDispatcher("_01_login/03_joinAction.jsp");
		dis.forward(request, response);
		
	}

}
