package com.Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connections.Connections;


@WebServlet("/Security")
public class Security extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Security() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		int status=0;
		String s=request.getParameter("on");
		System.out.println(s);
		HttpSession session=request.getSession(false);  
		String n=(String)session.getAttribute("uname");
		if(s==null)
		{
			try {
				Connection con=Connections.con();
				String query="update attacker set security='off' where owner_id='"+n+"'";
				PreparedStatement ps=con.prepareStatement(query);
				status=ps.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else
		{
			try {
				Connection con=Connections.con();
				String query="update attacker set security='on',status='deactive',ip='---' where owner_id='"+n+"'";
				PreparedStatement ps=con.prepareStatement(query);
				status=ps.executeUpdate();
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
			/*RequestDispatcher re=request.getRequestDispatcher("security.jsp");
			out.println("<html><body>");
			out.println("OFF");
			re.include(request, response);
			out.println("</body></html>");
			*/
			response.sendRedirect("security.jsp");
		
	}

}
