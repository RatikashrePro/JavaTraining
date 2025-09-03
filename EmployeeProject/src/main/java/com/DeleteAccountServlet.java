package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accno = req.getParameter("accno");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt","root","root");
            PreparedStatement ps = con.prepareStatement("DELETE FROM accountdetails WHERE accno=?");
            ps.setString(1, accno);
            int rows = ps.executeUpdate();
            out.println("<html><body style='text-align:center;'>");
            if(rows > 0){
                out.println("<h2 style='color:green;'>✅ Account Deleted Successfully</h2>");
            } else {
                out.println("<h2 style='color:red;'>❌ Account Not Found</h2>");
            }
            // 🔘 Home Button
            out.println("<br><br>");
            out.println("<a href='Home.html' " +
                        "style='display:inline-block;padding:12px 25px;" +
                        "background:#007bff;color:#fff;text-decoration:none;" +
                        "border-radius:8px;font-weight:bold;transition:0.3s;'>🏠 Go to Home</a>");

            out.println("</body></html>");

            con.close();
        } catch(Exception e){ out.println(e); }
    }
}
