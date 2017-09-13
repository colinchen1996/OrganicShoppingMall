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
 * Servlet implementation class deleteUserInfoAddressServlet
 */
public class deleteUserInfoAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUserInfoAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		String addressId = request.getParameter("AddressId");
		int i = Integer.valueOf(addressId).intValue();
		Address address = new Address();
		User user = new User();
		address.setAddressId(i);
		Object[] param = {address.getAddressId()};
		List<User> userlist = new UserDaoImpl().select("SELECT u.userName FROM userinfo u where u.userId = (SELECT userId FROM addressinfo where addressId = ?)", param);
		Object[] param1 = {userlist.get(0).getUserName() };
		Boolean boolean1 = new AddressDaoImpl().deleteAddress("DELETE from addressinfo WHERE addressId = ?", param);
		if (boolean1 == true) {			
//			List<User> userlist1 = new UserDaoImpl().select("SELECT * from userinfo where userName = ?", param1);
			List<Address> addresslist = new AddressDaoImpl().selectAddress("SELECT a.* from userinfo u,addressinfo a WHERE a.userId=u.userId AND u.userName = ?", param1);
			request.getSession().setAttribute("addresslist", addresslist);
			response.sendRedirect("userInfo.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
