package com;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class withdrawServlet
 */
//@WebServlet("/withdraw")
public class withdrawServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accno = req.getParameter("accno");
        double amount = Double.parseDouble(req.getParameter("amount"));
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt","root","root");
            PreparedStatement ps = con.prepareStatement("SELECT ini_bal FROM accountdetails WHERE accno=?");
            ps.setString(1, accno);
            ResultSet rs = ps.executeQuery();
            out.println("<html><body style='text-align:center;'>");
            if(rs.next()){
                double balance = rs.getDouble("ini_bal");
                if(balance >= amount){
                    double newBal = balance - amount;
                    PreparedStatement upd = con.prepareStatement("UPDATE accountdetails SET ini_bal=? WHERE accno=?");
                    upd.setDouble(1,newBal);
                    upd.setString(2,accno);
                    upd.executeUpdate();
                    out.println("<h2>✅ Withdraw Successful</h2><h3>💰 New Balance: ₹"+newBal+"</h3>");
                } else {
                    out.println("<h2 style='color:red;'>❌ Insufficient Balance</h2>");
                }
            } else {
                out.println("<h2 style='color:red;'>❌ Account Not Found</h2>");
            }
            out.println("</body></html>");
            con.close();
        } catch(Exception e){ out.println(e); }
    }
}

