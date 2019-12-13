package com.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.AdminBean;
import com.Bean.UserBean;
import com.Implementation.Implementation;
import com.Interfaces.Interface;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		String uid=request.getParameter("uid");
		String password=request.getParameter("password");
		
		UserBean bean=new UserBean();
		bean.setId(id);
		bean.setUid(uid);
		bean.setPassword(password);
		
		HttpSession session=request.getSession();  
        session.setAttribute("uname",id); 
        session.setAttribute("uid",uid); 
  
      String ip=InetAddress.getLocalHost().getHostAddress();
		
		Interface i=new Implementation();
		boolean b=i.block(ip);
		
		if(b==false)
		{
		int result=i.userLogin(bean);
		
		if(result==0)
		{
			
			
		     response.setContentType("text/html");  
			out.println("<html>");
	
			out.println("<body> ");
		
			
	
		
			out.println("<center><h2>Invalid ID or Password...<a href=index.html> Try again</a><h2></center>");
			
		
			out.println("</body></html>");
			
		}
		else if(result==1)
		{
			response.sendRedirect("user_welcome.jsp");
		}else
		{
			response.sendRedirect("attacked.jsp");
		}
		
		}else
		{
			  response.setContentType("text/html");  
				out.println("<html>");
		
				out.println("<body> ");
			
				
		
			
				out.println("<center><h2>Your IP is Blocked.<h2></center>");
				
			
				out.println("</body></html>");
		}
	}

}
