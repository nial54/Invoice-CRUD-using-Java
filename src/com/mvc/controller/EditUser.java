package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;
import com.mvc.bean.Data;

public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("update pro3 set firstname=?,lastname=?,address=?,phone=?,email=?,username=?,password=?,role=? where id=?");
				ps.setString(1, firstname);
				ps.setString(2, lastname);
				ps.setString(3, address);
				ps.setString(4, phone);
				ps.setString(5, email);
				ps.setString(6, username);
				ps.setString(7, password);
				ps.setString(8, role);
				ps.setInt(9, id);
					ps.executeUpdate();
					
					ps.close();
					conn.close();
					PrintWriter out = response.getWriter();
					out.println("<html><body><b>Successfully Updated" +"</b></body></html>");
					response.sendRedirect("http://localhost:8080/Pro3/admin.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Data getRecordById(int id) {
		Data user = null;
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("select*from pro3 where id=?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					user = new Data();
					user.setId(rs.getInt("id"));
					user.setFirstname(rs.getString("firstname"));
					user.setLastname(rs.getString("lastname"));
					user.setAddress(rs.getString("address"));
					user.setPhone(rs.getString("phone"));
					user.setEmail(rs.getString("email"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return user;
	}
}
