package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class createAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public createAccount() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String accno = request.getParameter("accno");
        String ifsc = request.getParameter("ifsc");
        String balance = request.getParameter("balance");
        String phone = request.getParameter("phone");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded....!");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
            PreparedStatement insertStmt = con.prepareStatement("insert into accountdetails values(?,?,?,?,?,?)");

            insertStmt.setString(1, id);
            insertStmt.setString(2, name);
            insertStmt.setString(3, accno);
            insertStmt.setString(4, ifsc);
            insertStmt.setString(5, balance);
            insertStmt.setString(6, phone);

            insertStmt.execute();
            insertStmt.close();
            con.close();

            // Redirect to home page with success message
            response.sendRedirect("Home.html?message=Account+created+successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Home.html?message=Error+creating+account");
        }
    }
}
