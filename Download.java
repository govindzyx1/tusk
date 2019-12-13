package com.User;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String file;
	private static final int buffsize=5667;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	/*doPost(request, response);*/
		System.out.println("do get method");
		doPost(request, response);
	}

	/**
	 * @param BUFSIZE 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			    throws ServletException, IOException {
		
	     int status=0;    
	   
	     
	  
		
		
		 
		 
		     String downloadpath=request.getRealPath("/");
		     downloadpath=downloadpath.substring(0,downloadpath.indexOf("."));
		     downloadpath=downloadpath+"ITJCC26_Admin\\WebContent\\Files\\all content\\";
		   
		     
		     String filename=request.getParameter("fname");
		     String username=request.getParameter("username");
		     
		     
		//     Filebean fd=new Filebean();
		//     fd.setUsername(username);
	//	     fd.setFilename(filename);
				
		     
		
		     
		     
		     downloadpath=downloadpath+filename;
		     System.out.println("the filename"+filename);
		     
		        System.out.println("ssssss"+" "+downloadpath);
		        try
		    
		         {
		    	
		               System.out.println("pathhhhhhhhhhhhhh   "+downloadpath);
		               File file = new File(downloadpath);
		               String  filenam=file.getName();
		               System.out.println(" Path "+downloadpath+"File name os "+filename);
		               int length = 0;
		               ServletOutputStream outStream =  response.getOutputStream();
		               ServletContext context = getServletConfig().getServletContext();
		               String mimetype = context.getMimeType(downloadpath);

		               if(mimetype == null)
		                {
		            	   
		                 mimetype = "application/octet-stream";
		                }
		                response.setContentType(mimetype);
		                response.setContentLength((int)file.length());

		                response.setHeader("Content-Disposition", "attachement; filename=\"" + filename + "\"");
		                byte[] byteBuffer = new byte[(int)file.length()];

		                DataInputStream dis =   new DataInputStream(new FileInputStream(file));

		                while((dis != null) && ((length = dis.read(byteBuffer)) != -1 ))
		                {
		                outStream.write(byteBuffer, 0, length);
		                }
		                dis.close();
		                outStream.close();
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		             
		    
	 
	}
	}



