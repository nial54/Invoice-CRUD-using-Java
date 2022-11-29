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

public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String cost = request.getParameter("cost");
		String product = request.getParameter("product");
		
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("update "+ product +"set name=?,cost=? where id=?");
				ps.setString(1, name);
				ps.setString(2, cost);
				ps.setInt(3, id);
					ps.executeUpdate();
					ps.close();
					conn.close();
					PrintWriter out = response.getWriter();
					out.println("<html><body><b>Successfully Updated" +"</b></body></html>");
					response.sendRedirect("http://localhost:8080/Pro3/foodlist.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Data getRecordById(int id,String product) {
		Data d = null;
		try
		{
			Connection conn = DBConnection.createConnection();
			PreparedStatement ps = conn.prepareStatement("select*from "+product+" where id=?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					d = new Data();
					d.setId(rs.getInt("id"));
					d.setName(rs.getString("name"));
					d.setCost(rs.getString("cost"));
				}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return d;
	}
}
