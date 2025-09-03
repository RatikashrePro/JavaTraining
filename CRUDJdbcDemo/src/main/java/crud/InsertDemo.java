package crud;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
//import java.util.Scanner;

public class InsertDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
		System.out.println("Connection established");
		Statement stmt= con.createStatement();
		stmt.addBatch("insert into Employee values(1,'kaniha',22,999999,'dev')");
		stmt.addBatch("insert into Employee values(2,'ipd',22,999999,'dev')");
		stmt.addBatch("insert into Employee values(1,'sree',22,999999,'dev')");
		stmt.addBatch("insert into Employee values(3,'mahe',22,999999,'dev')");
        stmt.executeBatch();
        stmt.close();
        con.close();
        System.out.println("Data inserted");
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
