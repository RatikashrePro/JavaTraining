package crud;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class InsertDynamic {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
			System.out.println("Connection established");
			PreparedStatement stmt= con.prepareStatement("insert into Employee values(?,?,?,?,?)");
			Scanner sc= new Scanner(System.in);
			int count=sc.nextInt();
			for(int i=0;i<count;i++) {
				System.out.println("Enter id: "); int id=sc.nextInt();
				System.out.println("Enter name: "); String name=sc.next();
				System.out.println("Enter age: "); int age=sc.nextInt();
				System.out.println("Enter salary: "); int salary=sc.nextInt();
				System.out.println("Enter designation: "); String desig=sc.next();
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setInt(3, age);
				stmt.setInt(4, salary);
				stmt.setString(5, desig);
				stmt.execute();
			}
			stmt.close();
			System.out.println("Data inserted successfully");
			sc.close();
			con.close();
			
		}
		catch(Exception e) {
		     System.out.println(e);	
		}
	}

}
