package com.mvc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mvc.util.DBConnection;

@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class ChangeImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// getting request value from form page
		int uid = Integer.parseInt(request.getParameter ("id"));
		String product = request.getParameter("product");
		Part ufile = request.getPart("photo");
		
		
		try {
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement ("update "+ product +"  set image=? where id=?");

			InputStream is = ufile.getInputStream();
			ps.setBlob(1, is);			
			ps.setInt(2, uid);
			// Execute the insert command using executeUpdate ()
			// to make changes in database
			ps.executeUpdate () ;
			// Close all the connections
			ps.close();
			conn.close();
			// Get a writer pointer
			// to display the successful result
			PrintWriter out = response.getWriter();
			out.println ("<html><body><b>Successfully Inserted"	+ "</b></body></html>") ;
			response.sendRedirect("./product.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}