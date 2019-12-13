package com.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.SetAllPropertiesRule;

import com.Bean.AdminBean;
import com.Implementation.Implementation;
import com.Interfaces.Interface;

/**
 * Servlet implementation class AdminRegister
 */
@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out=response.getWriter();
		
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String mail=request.getParameter("mail");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		
		AdminBean bean=new AdminBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPassword(password);
		bean.setMail(mail);
		bean.setMobile(mobile);
		bean.setAddress(address);
		bean.setCity(city);
		
		Interface i=new Implementation();
		int result=i.adminRegister(bean);
		
		if(result==1)
		{
			out.println("<html><body>Registeration Success...<a href=admin.html>Login</a></body></html>");
		}
		else
		{
			out.println("<html><body>Registeration Failed...<a href=admin.html>Try Again</a></body></html>");
		}
		
		
		
		
	}

}
