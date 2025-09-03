package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public FirstServlet() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("id");
	     String name = request.getParameter("name");
	     String age = request.getParameter("age");
	     String salary = request.getParameter("salary");
	     String desig = request.getParameter("desig");
	     response.setContentType("text/html");
	     PrintWriter out= response.getWriter();
	     out.print("<body style='text-align:center; font-family:Arial, sans-serif; background-color: beige;'>");
	     out.print("<div style='margin-top:50px;'>");
	     out.print("<h1>Employee Details Submitted</h1>");
	     out.print("<p><b>ID:</b> " + id + "</p>");
	     out.print("<p><b>Name:</b> " + name + "</p>");
	     out.print("<p><b>Age:</b> " + age + "</p>");
	     out.print("<p><b>Salary:</b> " + salary + "</p>");
	     out.print("<p><b>Designation:</b> " + desig + "</p>");
	     out.print("</div>");
	     out.print("</body>");
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("Driver loaded....!");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
         PreparedStatement insertStmt= con.prepareStatement("insert into Employee values(?,?,?,?,?)");
        
	     insertStmt.setString(1, id);
         insertStmt.setString(2,name);
         insertStmt.setString(3, age);
         insertStmt.setString(4, salary);
         insertStmt.setString(5, desig);
         insertStmt.execute();
         insertStmt.close();
		 }catch(Exception e) {
			 System.out.println(e);
		 }

		
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
