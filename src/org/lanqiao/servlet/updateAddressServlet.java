package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Address;
import org.lanqiao.entity.User;
import org.lanqiao.impl.AddressDaoImpl;
import org.lanqiao.impl.UserDaoImpl;

/**
 * Servlet implementation class updateAddressServlet
 */
public class updateAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String street = request.getParameter("street");
		String userName = request.getParameter("name");
		
		Address address = new Address();
		address .setProvince(province);
		address .setCity(city);
		address .setArea(area);
		address .setStreet(street);
		User user = new User();
		user.setUserName(userName);
		Object[] param = {user.getUserName()};
		List<User> userlist = new UserDaoImpl().select("SELECT u.userId FROM userinfo u where u.userName = ?", param);
		Object[] param1 ={userlist.get(0).getUserId(),address.getProvince(),address.getCity(),address.getArea(),address.getStreet()};
		Boolean boolean1 = new AddressDaoImpl().updateAddress("INSERT into addressinfo (userId, province, city, area, street) VALUES(?,?,?,?,?)", param1);		
		if (boolean1 == true) {
			List<Address> addresslist = new AddressDaoImpl().selectAddress("SELECT a.* from userinfo u,addressinfo a WHERE a.userId=u.userId AND u.userName = ?", param);
			request.getSession().setAttribute("addresslist", addresslist);
			response.getWriter().print(1);
		}
	
	}

}
