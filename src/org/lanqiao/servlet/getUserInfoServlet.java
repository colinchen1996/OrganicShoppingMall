package org.lanqiao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Address;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderItem;
import org.lanqiao.entity.Product;
import org.lanqiao.entity.ProductItem;
import org.lanqiao.entity.User;
import org.lanqiao.impl.AddressDaoImpl;
import org.lanqiao.impl.OrderDaoImpl;
import org.lanqiao.impl.OrderItemDaoImpl;
import org.lanqiao.impl.ProductDaoImpl;
import org.lanqiao.impl.ProductItemDaoImpl;
import org.lanqiao.impl.UserDaoImpl;

/**
 * Servlet implementation class yinTestServlet
 */
public class getUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		request.setCharacterEncoding("utf-8"); 
		String userName = request.getParameter("name");
		//String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumeber = request.getParameter("phoneNumeber");
		String address = request.getParameter("address");
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumeber);
		List<Object> list1 = new ArrayList<Object>() ;
		List<Object> list2 = new ArrayList<Object>();
		List<Object> list3 = new ArrayList<Object>();
		
		// 3.执行dao中方法
		
			Object[] param = {user.getUserName()};
			List<User> userlist = new UserDaoImpl().select("SELECT * from userinfo where userName = ? ", param);
			List<Address> addresslist = new AddressDaoImpl().selectAddress("SELECT a.* from userinfo u,addressinfo a WHERE a.userId=u.userId AND u.userName = ?", param);
		
			Object[] param4 = {userlist.get(0).getUserId()}; 
			List<Order> orderlist = new OrderDaoImpl().select("SELECT DISTINCT i.*,s.orderStatus from orderinfo i, orderstatusinfo s where i.orderStatusId = s.orderStatusId and i.userId = ? ", param4);
			for (int i = 0; i < orderlist.size(); i++) {
				int i1=orderlist.get(i).getOrderId();
				int i2=orderlist.get(i).getAddressId();
				Object[] param1 = {i1}; 
				Object[] param2 = {i2};
				
				List<OrderItem> OrderItemlist = new OrderItemDaoImpl().select("SELECT * from orderitem where orderId = ?", param1);
				List<Product> productlist = new ProductDaoImpl().select("SELECT p.* from productinfo p,orderinfo o,orderitem oo where o.orderId = oo.orderId AND oo.productId = p.productId and o.orderId =?",param1);
				List<Address> addresslist1 = new AddressDaoImpl().selectAddress("SELECT * from addressinfo WHERE addressId= ?", param2);
				list1.add(addresslist1);
				list2.add(productlist);
				list3.add(OrderItemlist);
			}
			
			
			
			request.getSession().setAttribute("list", userlist);
			request.getSession().setAttribute("addresslist", addresslist);
			request.getSession().setAttribute("orderlist", orderlist);
			request.getSession().setAttribute("list1", list1);
			request.getSession().setAttribute("list2",list2);
			request.getSession().setAttribute("list3",list3);
			
			// request.getRequestDispatcher("userInfo.jsp").forward(request, response);
			response.sendRedirect("userInfo.jsp");
			//response.getWriter().print(1);
	}

}
