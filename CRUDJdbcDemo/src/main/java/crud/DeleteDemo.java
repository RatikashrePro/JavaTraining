package crud;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
			System.out.println("Connection established");
			PreparedStatement stmt= con.prepareStatement("delete from Employee where eid= ?");
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter id to delete:");
			int id=sc.nextInt();
			System.out.println("Do you really want to delete? Y/N:");
			String ch=sc.next();
			if(ch.equalsIgnoreCase("Y") || ch.equalsIgnoreCase("yes")) {
				stmt.setInt(1, id);
				stmt.execute();
				System.out.println("Deleted successfully....!");
			}
			else {
				System.out.println("Not deleted...!");
			}
			stmt.close();
			con.close();
			sc.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
