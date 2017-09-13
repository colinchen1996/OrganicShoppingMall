package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Address;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderItem;
import org.lanqiao.entity.Product;
import org.lanqiao.entity.User;
import org.lanqiao.impl.AddressDaoImpl;
import org.lanqiao.impl.OrderDaoImpl;
import org.lanqiao.impl.OrderItemDaoImpl;
import org.lanqiao.impl.ProductDaoImpl;

/**
 * Servlet implementation class getOrderInfoservlet
 */
public class getOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getOrderInfoServlet() {
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
		String userId = request.getParameter("userId");
		int i = Integer.valueOf(userId).intValue();
		User user = new User();
		user.setUserId(i);
		Object[] param = {user.getUserId()}; 
		List<Order> orderlist = new OrderDaoImpl().select("SELECT * from orderinfo where userId = ?", param);
		request.getSession().setAttribute("orderlist", orderlist);
		
		Object[] param1 = {orderlist.get(0).getOrderId()}; 
		Object[] param2 = {orderlist.get(0).getAddressId()}; 
		List<OrderItem> orderItemlist = new OrderItemDaoImpl().select("SELECT * from orderItem where orderId = ?", param1);
		List<Address> addresslist = new AddressDaoImpl().selectAddress("SELECT * from addressinfo WHERE addressId= ?", param2);
		request.getSession().setAttribute("orderItemlist", orderItemlist);
		request.getSession().setAttribute("addresslist", addresslist);
		
		Object[] parma3 = {orderItemlist.get(0).getProductId()};
		List<Product> productlist = new ProductDaoImpl().select("SELECT * from productinfo WHERE productId= ?",parma3);
		request.getSession().setAttribute("productlist",productlist);
		response.sendRedirect("userInfo.jsp");

	}

}
