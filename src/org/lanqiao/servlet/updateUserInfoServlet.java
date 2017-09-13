package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.impl.UserDaoImpl;

/**
 * Servlet implementation class userInfoServlet
 */
public class updateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateUserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumeber = request.getParameter("phoneNumeber");

		// 2.创建user对象
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumeber);

		// 3.执行dao中方法
		Object[] param = { user.getEmail(), user.getPhoneNumber(), user.getUserName() };
		Boolean boolean1 = new UserDaoImpl()
				.update("UPDATE userinfo u SET u.email= ? ,u.phoneNumber= ? WHERE u.userName= ? ", param);
		if (boolean1 == true) {
			Object[] param1 = { user.getUserName() };
			List<User> userlist = new UserDaoImpl().select("SELECT * from userinfo where userName = ?", param1);
			request.getSession().setAttribute("list", userlist);
			response.sendRedirect("userInfo.jsp");
		}
		
	}
}

