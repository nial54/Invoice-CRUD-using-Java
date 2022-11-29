package com.mvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.util.DBConnection;

public class validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
	        String username = request.getParameter("username");   
	        String password = request.getParameter("password");
	        Class.forName("com.mysql.jdbc.Driver"); 
	        Connection conn = DBConnection.createConnection();   
	        PreparedStatement ps = conn.prepareStatement("Select id,username,password,role from pro3 where username=? and password=?");
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ResultSet rs = ps.executeQuery();                        
	        HttpSession session = request.getSession();
			if(rs.next())
	        	if(rs.getString("role").equals("admin"))
	        	{
			        session.setAttribute("username", username);//THIS IS HOW WE DECLARE THE USER IN A SESSION
			        session.setAttribute("id", rs.getString("id"));
			        session.setAttribute("role", rs.getString("role"));
					response.sendRedirect("admin.jsp");
	        	}
	        	if(rs.getString("role").equals("user"))
	        	{
	        		session.setAttribute("username", username);//THIS IS HOW WE DECLARE THE USER IN A SESSION
	        		session.setAttribute("role", rs.getString("role"));
	        		session.setAttribute("id", rs.getString("id"));
					response.sendRedirect("user.jsp");
	        	}	
	        	else
	           System.out.println("Invalid login credentials");            
	   }
	   catch(Exception e)
		{       
	       System.out.println("Something went wrong !! Please try again");       
	   }      
	}

}
