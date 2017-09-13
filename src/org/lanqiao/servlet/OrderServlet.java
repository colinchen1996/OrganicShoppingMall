package org.lanqiao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Product;
import org.lanqiao.impl.CartDaoImpl;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map productMap = (HashMap)request.getSession().getAttribute("productMap");
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		List<Product> list=new ArrayList<Product>();
		List countlist=new ArrayList();
		Iterator iter = productMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			int productId = (int) entry.getKey();
			int count = (int) entry.getValue();
			countlist.add(count);
			List<Product> cartList = cartDaoImpl.getProductById(productId);
			list.add((Product)cartList.get(0));
		}
		request.setAttribute("list",list);
		request.setAttribute("countlist",countlist);
		request.getRequestDispatcher("order_index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
