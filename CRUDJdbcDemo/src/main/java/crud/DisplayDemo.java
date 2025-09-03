package crud;

import java.sql.Connection;
import java.sql.*;

public class DisplayDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
			System.out.println("Connection established");
			Statement stmt= con.createStatement();
			ResultSet rs= stmt.executeQuery("Select * from Employee");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" : "+ rs.getString(2));
			}
						stmt.close();
			con.close();
			
		}
		catch(Exception e) {
			
		}

	}

}
