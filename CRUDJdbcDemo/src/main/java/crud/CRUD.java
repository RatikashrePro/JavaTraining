package crud;
import java.sql.*;
import java.util.Scanner;
 
public class CRUD {
 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded....!");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
            System.out.println("Connection Established....!");
            int choice;
            do {
                System.out.println("\n===== CRUD MENU =====\n");
                System.out.println("1. Insert Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
 
                switch (choice) {
                    case 1:
                        PreparedStatement insertStmt = con.prepareStatement(
                                "INSERT INTO Employee VALUES (?, ?, ?, ?, ?)");
                        System.out.print("How many employees do you want to insert? ");
                        int n = sc.nextInt();
 
                        for (int i = 1; i <= n; i++) {
                            System.out.println("\n--- Employee " + i + " ---\n");
                            System.out.print("ID: ");
                            int id = sc.nextInt();
                            System.out.print("Name: ");
                            String name = sc.next();
                            System.out.print("Age: ");
                            int age = sc.nextInt();
                            System.out.print("Salary: ");
                            double salary = sc.nextDouble();
                            System.out.print("Designation: ");
                            String desig = sc.next();
 
                            insertStmt.setInt(1, id);
                            insertStmt.setString(2, name);
                            insertStmt.setInt(3, age);
                            insertStmt.setDouble(4, salary);
                            insertStmt.setString(5, desig);
 
                            insertStmt.execute();
                        }
                        insertStmt.close();
                        System.out.println("Data inserted successfully!");
                        break;
 
                    case 2:
                        Statement selectStmt = con.createStatement();
                        ResultSet rs = selectStmt.executeQuery("SELECT * FROM Employee");
 
                        System.out.println("\nID | Name | Age | Salary | Designation\n");
                        System.out.println("------------------------------------------");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " +rs.getInt(3) + " | " +rs.getDouble(4) + " | " + rs.getString(5));
                        }
                        rs.close();
                        selectStmt.close();
                        break;
 
                    case 3:
                        PreparedStatement updateStmt = con.prepareStatement("UPDATE Employee SET salary = ? WHERE eid = ?");
                        System.out.print("Enter Employee ID to update salary: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();
 
                        updateStmt.setDouble(1, newSalary);
                        updateStmt.setInt(2, updateId);
 
                        int updatedRows = updateStmt.executeUpdate();
                        if (updatedRows > 0) {
                            System.out.println("Salary updated successfully!");
                        } else {
                            System.out.println("No employee found with that ID.");
                        }
                        updateStmt.close();
                        break;
 
                    case 4:
                        PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM Employee WHERE eid = ?");
                        System.out.print("Enter Employee ID to delete: ");
                        int deleteId = sc.nextInt();
                        System.out.print("Do you really want to delete? (Y/N): ");
                        String confirm = sc.next();
 
                        if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("YES")) {
                            deleteStmt.setInt(1, deleteId);
                            int deletedRows = deleteStmt.executeUpdate();
                            if (deletedRows > 0) {
                                System.out.println("Data deleted successfully!");
                            } else {
                                System.out.println("No employee found with that ID.");
                            }
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                        deleteStmt.close();
                        break;
 
                    case 5:
                        System.out.println("Exiting program...");
                        break;
 
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
 
            } while (choice != 5);
 
            // Close resources
            sc.close();
            con.close();
 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
 
		        
 
	}
 
}