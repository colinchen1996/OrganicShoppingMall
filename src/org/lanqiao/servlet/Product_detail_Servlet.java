package org.lanqiao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.lanqiao.entity.Product;
import org.lanqiao.entity.ProductImgInfo;
import org.lanqiao.impl.ProductDaoImpl;
import org.lanqiao.impl.ProductImgInfoDaoImpl;

/**
 * Servlet implementation class Product_detail_Servlet
 */
public class Product_detail_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product_detail_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("idd");
		int id1= (id == null) ? 1 :Integer.parseInt(id);
		Product product = new Product();
		product.setProductId(id1);
		ProductDaoImpl productDapInmpl = new ProductDaoImpl();
		List<Product> productsList = productDapInmpl.selectId(product);
		List<Product> productsList1 = productDapInmpl.selectType(product);
		
		ProductImgInfo productImgInfo = new ProductImgInfo();
		productImgInfo.setProductId(id1);
		ProductImgInfoDaoImpl productImgInfoDaoImpl = new ProductImgInfoDaoImpl();
		List<ProductImgInfo> productImgInfoList = productImgInfoDaoImpl.selectImgInfo(productImgInfo);
		
		
		  
        String list0 = "";  
          
        //从客户端获取cookie集合，遍历cookie集合  
        Cookie[] cookies = request.getCookies();  
        if(null != cookies && cookies.length > 0){  
            for(Cookie cookie : cookies){  
                if(cookie.getName().equals("ListIdCookie")){  
                    list0 = cookie.getValue();  
                }  
            }  
        }  
        //以逗号进行分隔  
        list0 += id + ",";  
        //如果cookie中的记录过多，则清零  
        String[] arr = list0.split(",");  
        List<Product> productsList3=new ArrayList<Product>();
        if(arr != null && arr.length > 0){  
            if(arr.length >= 50){  
                list0 = "";  
            }  
            int id2;
            for(String s : arr){
            	id2 = Integer.parseInt(s);
            	productsList3.add(productDapInmpl.selectHistory(id2));
            	
            }
        }  
          
        Cookie newCookie = new Cookie("ListIdCookie",list0);  
        //设置cookie的有效期  
        newCookie.setMaxAge(24*60*60);  
          
        response.addCookie(newCookie); 

		
		
		request.setAttribute("productsList", productsList);
		request.setAttribute("productsList1", productsList1);
		request.setAttribute("productsList3", productsList3);
		request.setAttribute("productImgInfoList", productImgInfoList);
		request.getRequestDispatcher("product_detail.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
