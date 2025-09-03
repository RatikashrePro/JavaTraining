package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
	     String password = request.getParameter("password");
	     PrintWriter out= response.getWriter();
	     
	     try {
	            // 1. Load Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	 
	            // 2. Establish connection
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
	 
	            // 3. Query to check user credentials
	            String sql = "SELECT * FROM userlogindetails WHERE username=? AND userpass=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, name);
	            ps.setString(2, password);
	 
	            ResultSet rs = ps.executeQuery();
	 
	            if (rs.next()) {
	                // ✅ If user exists → login success
	                RequestDispatcher rd = request.getRequestDispatcher("Home.html");
	                rd.forward(request, response);
	            } else {
	                // ❌ Invalid login → back to login page
	                out.println("<div><p style='color:red; text-align:center;'>Invalid Username or Password ❌</p>");
	                RequestDispatcher rd = request.getRequestDispatcher("Login.html");
	                rd.include(request, response);
	                out.println("</div>");
	            }
	 
	            // Close resources
	            rs.close();
	            ps.close();
	            con.close();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
	        }
	     
	     
	     
	   
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
