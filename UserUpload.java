package com.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.UploadFileBean;
import com.Implementation.Implementation;
import com.Interfaces.Interface;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;

/**
 * Servlet implementation class UserUpload
 */
@WebServlet("/UserUpload")
public class UserUpload extends HttpServlet {
	static String filepath;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpload() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Inside servlet");

		MultipartParser mp = new MultipartParser(request, 99999999);
		com.oreilly.servlet.multipart.Part part = null;
		FilePart filePart = null;
		ParamPart paramPart = null;
		String filename = null;
		String type = null;
		String path = null;
	/*	String key = "10001";
		String dictionary = null;

		
		String Adminfilename;
		String Adminpath;
		
*/	
		String toUpload = null;
	//	String username=null;
		double fileSize = 0;
		
	//	HttpSession session=request.getSession();
	//	username=(String) session.getAttribute("username");

		ArrayList<String> paramValues = new ArrayList<String>();
		
		UploadFileBean ufb = new UploadFileBean(); // obj for bean class

		String folderPath=request.getRealPath("/");
		folderPath=folderPath.substring(0, folderPath.indexOf("."));
		folderPath=folderPath+"ITJCC03\\WebContent\\Files";
	
		while ((part = mp.readNextPart()) != null) {
			if (part.isFile()) {
				filePart = (FilePart) part;
				toUpload = toUpload + filePart.getFileName();

				String checkfile = filePart.getFileName();
				filename = filePart.getFileName();
				type = filePart.getContentType();
				File files = new File(folderPath+"\\all content\\"+ checkfile);
				fileSize=filePart.writeTo(files);
				ufb.setSize(""+fileSize);
				if (checkfile.endsWith(".pdf")) {
					filepath = folderPath + "\\pdf\\" + checkfile;
					File file = new File(filepath);
					FileInputStream fis = new FileInputStream(files);
					byte[] b = new byte[fis.available()];
					fis.read(b);
					FileOutputStream fileOutputStream = new FileOutputStream(
							file);
					fileOutputStream.write(b);
					ufb.setUploadcontent(fis);
					fileOutputStream.close();
				} else if (checkfile.endsWith(".txt")) {
					System.out.println("FolderPath="+folderPath);
					filepath = folderPath + "\\text\\" + checkfile;
					File file = new File(filepath);
					FileInputStream fis = new FileInputStream(files);
					byte[] b = new byte[fis.available()];
					fis.read(b);
					FileOutputStream fileOutputStream = new FileOutputStream(
							file);
					fileOutputStream.write(b);
					fileOutputStream.close();
				}

				else if (checkfile.endsWith(".doc")) {

					filepath = folderPath + "\\doc\\" + checkfile;
					System.out.println("filepath" + filepath);
					File file = new File(filepath);
					fileSize = filePart.writeTo(file);
					FileInputStream fis = new FileInputStream(files);
					byte[] b = new byte[fis.available()];
					fis.read(b);
					FileOutputStream fileOutputStream = new FileOutputStream(
							file);
					fileOutputStream.write(b);
					fileOutputStream.close();
				}

				try {
				//	security.main(null);
				} catch (Exception e) {
					System.out.println("AdminUpload.doPost()");
					e.printStackTrace();
				}
			} else if (part.isParam()) {
				paramPart = (ParamPart) part;
				String tagName = paramPart.getName();
				System.out.println("tagName = " + tagName);
				String tagValue = paramPart.getStringValue().trim();
				System.out.println("tagValue = " + tagValue);

				paramValues.add(tagValue);

			}

		}
		
		Iterator<String> it = paramValues.iterator();
		while (it.hasNext()) {
			ufb.setKey(it.next().toString());
			//ufb.setD_passibleOfWords(it.next().toString());
			//ufb.setTime(it.next().toString());
		}
		System.out.println(paramValues + "key");
		System.out.println(paramValues);
		
	
		HttpSession session1=request.getSession(false);  
		String n=(String)session1.getAttribute("uname");
		String uid=(String)session1.getAttribute("uid");


		 
		
		
		
		path = filepath;
		ufb.setFilename(filename);
		ufb.setType(type);
		ufb.setPath(path);
		ufb.setId(n);
		ufb.setUid(uid);
		
		System.out.println("path=" + path);
		int status = 0;
		System.out.println("its here");
		Interface I =new Implementation();
		status = I.userUpload(ufb);
		System.out.println(status);
		System.out.println("status  " + status);
		if (status==1) {
			response.sendRedirect("user_success.jsp");
		} else if(status==0) {
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("<h2>Upload Failed...</h2><a href=user_upload.jsp>Try again</a>");
			out.println("</body></html>");
		}else
		{
			response.sendRedirect("attacked.jsp");
		}

	}

}
