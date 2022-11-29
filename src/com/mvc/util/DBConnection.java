package com.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection createConnection()
	 {
	     Connection conn = null;
	     String url = "jdbc:mysql://localhost:3306/project"; 
	     String username = "root"; 
	     String password = "Root"; 
	     System.out.println("In DBConnection.java class ");
	      
	     try
	     {
	         try
	         {
	            Class.forName("com.mysql.cj.jdbc.Driver"); 
	         } 
	         catch (ClassNotFoundException e)
	         {
	            e.printStackTrace();
	         }       
	         conn = DriverManager.getConnection(url, username, password); 
	     } 
	     catch (Exception e) 
	     {
	        e.printStackTrace();
	     }   
	     return conn; 
	 }
}