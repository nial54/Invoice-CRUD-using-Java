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

@MultipartConfig(maxFileSize = 16177216)
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String cost = request.getParameter("cost");
		String product = request.getParameter("product");
		Part image = request.getPart("image");
		
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("insert into product(name,cost,product,image) values(?,?,?,?)");
				ps.setString(1, name);
				ps.setString(2, cost);
				ps.setString(3, product);
					InputStream is = image.getInputStream();
				ps.setBlob(4, is);
					ps.executeUpdate();
					ps.close();
					conn.close();
					PrintWriter out = response.getWriter();
					out.println("<html><body><b>Successfully Inserted" +"</b></body></html>");
					response.sendRedirect("http://localhost:8080/Pro3/product.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Something Error" +"</b></body></html>");
		}
	}

}
