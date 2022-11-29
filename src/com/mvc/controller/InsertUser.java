package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@MultipartConfig(maxFileSize = 16177216)
public class InsertUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = "user";
		
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("insert into pro3(firstname,lastname,address,phone,email,username,password,role) values(?,?,?,?,?,?,?,?)");
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, address);
				ps.setString(4, phone);
				ps.setString(5, email);
				ps.setString(6, username);
				ps.setString(7, password);
				ps.setString(8, role);
					ps.executeUpdate();
					
					ps.close();
					conn.close();
					PrintWriter out = response.getWriter();
					out.println("<html><body><b>Successfully Inserted" +"</b></body></html>");
					response.sendRedirect("http://localhost:8080/Pro3/");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Something Error" +"</b></body></html>");
		}
	}

}
