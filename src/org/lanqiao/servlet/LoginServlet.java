package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.lanqiao.entity.User;
import org.lanqiao.impl.UserDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User user = new User();
		user.setUserName(request.getParameter("name"));
		user.setUserPass(request.getParameter("password"));
		// session验证登录

		List<User> list = new UserDaoImpl().login(user);
		if (list != null) {
			if (user.getUserName().equals("admin")) {
				response.getWriter().print("admin");
			} else if (list.get(0).getUserStatus().equals("已锁定")) {
				response.getWriter().print("已锁定"); 
			} else {
				request.getSession().setAttribute("name",
						list.get(0).getUserName());
				request.getSession().setMaxInactiveInterval(10 * 60);
				// response.sendRedirect("index.jsp");
				response.getWriter().print("true");
			}

		} else {
			request.getSession().setAttribute("error", "密码错误，请重新登录！");
			// response.sendRedirect("login.jsp");
			response.getWriter().print("false");
		}
	}

}
