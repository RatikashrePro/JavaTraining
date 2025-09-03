package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deposit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accno = request.getParameter("accno");
        double amount = Double.parseDouble(request.getParameter("amount"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect DB
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Prodapt", "root", "root");

            // 3. First check account exists
            PreparedStatement psCheck = con.prepareStatement(
                    "SELECT ini_bal FROM accountdetails WHERE accno=?");
            psCheck.setString(1, accno);
            ResultSet rs = psCheck.executeQuery();

            out.println("<html><body style='font-family:Segoe UI;text-align:center;'>");

            if (rs.next()) {
                double oldBalance = rs.getDouble("ini_bal");
                double newBalance = oldBalance + amount;

                // 4. Update Balance
                PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE accountdetails SET ini_bal=? WHERE accno=?");
                psUpdate.setDouble(1, newBalance);
                psUpdate.setString(2, accno);

                int updated = psUpdate.executeUpdate();

                if (updated > 0) {
                    out.println("<h2 style='color:green;'>✅ Deposit Successful!</h2>");
                    out.println("<h3>💰 New Balance: ₹ " + newBalance + "</h3>");
                } else {
                    out.println("<h2 style='color:red;'>❌ Deposit Failed!</h2>");
                }
            } else {
                out.println("<h2 style='color:red;'>❌ Account not found!</h2>");
            }

            out.println("</body></html>");
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
