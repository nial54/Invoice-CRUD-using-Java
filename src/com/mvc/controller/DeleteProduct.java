package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String product = request.getParameter("product");
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("delete from "+product+" where id=?");
				ps.setInt(1, id);
				ps.executeUpdate();
				ps.close();
				conn.close();
				response.sendRedirect("admin.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}