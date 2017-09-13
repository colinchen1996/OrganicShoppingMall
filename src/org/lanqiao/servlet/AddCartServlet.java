package org.lanqiao.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Product;

/**
 * Servlet implementation class AddCartServlet
 */
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("add".equals(request.getParameter("action"))){
		if(request.getSession().getAttribute("productMap")==null){
			Map productMap = new HashMap();
			int productId = Integer.parseInt(request.getParameter("productId"));
			int count = Integer.parseInt(request.getParameter("count"));
		//	request.getSession().setAttribute("count",count);
			productMap.put(productId, count);
			request.getSession().setAttribute("productMap", productMap);
			request.getSession().setAttribute("count", count);
			response.getWriter().print(count);
		}else{
			Map productMap=(HashMap)request.getSession().getAttribute("productMap");
			int productId = Integer.parseInt(request.getParameter("productId"));
			int count = Integer.parseInt(request.getParameter("count"));
			Iterator iter = productMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				if(productId == (int) entry.getKey())
				count = (int) entry.getValue() + 1;
			}
			productMap.put(productId, count);
			request.getSession().setAttribute("productMap", productMap);
			request.getSession().setAttribute("count", count);
			response.getWriter().print(count);
		}
		}else if("totalcount".equals(request.getParameter("action"))){
			if(request.getSession().getAttribute("productMap")!=null){
			Map productMap=(HashMap)request.getSession().getAttribute("productMap");
			int totlecount = 0;
			Iterator iter = productMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				totlecount += (int) entry.getValue();
			}
			response.getWriter().print(totlecount);
			}
			else{
				int totlecount = 0;
				response.getWriter().print(totlecount);
			}
		}
		//request.getSession().setAttribute("productMap", productMap);
		//HashMap productMap = new HashMap();
		//CartDaoImpl cartDaoImpl = new CartDaoImpl();
//		Iterator iter = productMap.entrySet().iterator();
//		Map.Entry entry = (Map.Entry) iter.next();
//		if ((int)entry.getKey() == 1) {		
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
