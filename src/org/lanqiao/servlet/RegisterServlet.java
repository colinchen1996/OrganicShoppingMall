package org.lanqiao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.impl.UserDaoImpl;
import org.lanqiao.dao.*;;
/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter p=response.getWriter();   
		String name=null;
		String email=null;
		String phone=null;
		if(request.getParameter("name")!=null){
		    name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");

		}else if(request.getParameter("email")!=null){
		    email = new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
	
		}else{
		    phone = new String(request.getParameter("phone").getBytes("iso-8859-1"),"utf-8");

		}
	     
	    User user = new User();	
		user.setUserName(name);
		user.setEmail(email);
		user.setPhoneNumber(phone);		
	    if(new UserDaoImpl().registerCheck(user)){
				p.print("true");
				}
			else{
			    p.print("flase");
			}	        
			p.flush();
			p.close();
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		   
		User user = new User();	
		
		
		user.setUserName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phone"));
		user.setUserPass(request.getParameter("password"));
		
		boolean ret=new UserDaoImpl().register(user);
		if(ret){
			response.getWriter().print("true");
			//response.sendRedirect("login.jsp");
		}
		else{
			response.getWriter().print("false");
			//response.sendRedirect("register.jsp");
		}
			
		
	}

}
