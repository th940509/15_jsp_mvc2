package _01_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_login.dao.MemberDAO;

@WebServlet("/loginAction.do")
public class _05_LoginAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	public void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		boolean isLogin = MemberDAO.getInstance().loginMember(id, pw);
		
		if (isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("memId", id);
		}
		
		request.setAttribute("isLogin", isLogin);
		
		RequestDispatcher dis = request.getRequestDispatcher("_01_login/05_loginAction.jsp");
		dis.forward(request, response);
		
	}

}
