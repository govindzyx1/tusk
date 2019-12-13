package com.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.AdminBean;
import com.Implementation.Implementation;
import com.Interfaces.Interface;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		AdminBean bean=new AdminBean();
		bean.setId(id);
		bean.setPassword(password);
		
		HttpSession session=request.getSession();  
        session.setAttribute("uname",id);  
  
		
		Interface i=new Implementation();
		int result=i.adminLogin(bean);
		
		if(result==0)
		{
			  response.setContentType("text/html");  
				out.println("<html>");
		
				out.println("<body> ");
			
				
		
			
				out.println("<center><h2>Invalid ID or Password...<a href=admin.html> Try again</a><h2></center>");
				
			
				out.println("</body></html>");
		}
		else if(result==1)
		{
			response.sendRedirect("admin_welcome.jsp");
		}
		
		
	}

}
