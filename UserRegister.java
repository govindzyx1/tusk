package com.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.AdminBean;
import com.Bean.UserBean;
import com.Implementation.Implementation;
import com.Interfaces.Interface;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
			
			String id=request.getParameter("id");
			String uid=request.getParameter("uid");
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			String mail=request.getParameter("mail");
			String mobile=request.getParameter("mobile");
			String address=request.getParameter("address");
			String city=request.getParameter("city");
			
			UserBean bean=new UserBean();
			bean.setId(id);
			bean.setUid(uid);
			bean.setName(name);
			bean.setPassword(password);
			bean.setMail(mail);
			bean.setMobile(mobile);
			bean.setAddress(address);
			bean.setCity(city);
			
			Interface i=new Implementation();
			int result=i.userRegister(bean);
			
			if(result==1)
			{
				out.println("<html><body>Registeration Success...<a href=indaex.html>Login</a></body></html>");
			}
			else
			{
				out.println("<html><body>Registeration Failed...<a href=index.html>Try Again</a></body></html>");
			}
			
			
			
	}

}
