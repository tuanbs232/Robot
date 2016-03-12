package org.firestorm.demosecurities.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.firestorm.demosecurities.database.User;
import org.firestorm.demosecurities.database.UserDAO;

/**
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
			return;
		}
		if (id.equals("logout")) {
			HttpSession session = request.getSession(false);
			session.removeAttribute("user");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = UserDAO.getUser(username, password);
		if (user == null) {
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			request.setCharacterEncoding("utf-8");
			request.setAttribute("error",
					"[ERROR] Tên tài khoản hoặc mật khẩu không chính xác");
			view.forward(request, response);
		} else {
			HttpSession session = request.getSession(false);
			session.setMaxInactiveInterval(60 * 60);
			session.setAttribute("user", user);
			response.sendRedirect("SignatureServlet");
		}
	}

}