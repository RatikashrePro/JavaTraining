package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
			System.out.println("Connection established");
			PreparedStatement stmt= con.prepareStatement("update Employee set salary = ? where eid = ?");
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter id to update:");
			int id=sc.nextInt();
			System.out.println("Enter salary:");
			int salary= sc.nextInt();
				stmt.setInt(1, salary);
				stmt.setInt(2, id);
				stmt.execute();
				System.out.println("Updated successfully....!");
		
			stmt.close();
			con.close();
			sc.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
