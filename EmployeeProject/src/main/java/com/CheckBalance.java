package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckBalance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accno = request.getParameter("accno");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to DB (adjust DB name, user, password)
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Prodapt", "root", "root");

            // 3. Query Balance
            PreparedStatement ps = con.prepareStatement(
                    "SELECT ini_bal FROM accountdetails WHERE accno = ?");
            ps.setString(1, accno);
            ResultSet rs = ps.executeQuery();

            out.println("<html><body style='font-family:Segoe UI;text-align:center;'>");
            if (rs.next()) {
                double balance = rs.getDouble("ini_bal");
                out.println("<h2 style='color:green;'>✅ Account Balance: ₹ " + balance + "</h2>");
            } else {
                out.println("<h2 style='color:red;'>❌ Account not found!</h2>");
            }
            // 🔘 Home Button
            out.println("<br><br>");
            out.println("<a href='Home.html' " +
                        "style='display:inline-block;padding:12px 25px;" +
                        "background:#007bff;color:#fff;text-decoration:none;" +
                        "border-radius:8px;font-weight:bold;transition:0.3s;'>🏠 Go to Home</a>");

            out.println("</body></html>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
