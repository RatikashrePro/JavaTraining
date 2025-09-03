package crudProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
 
abstract class Emp {
    Scanner sc = new Scanner(System.in);
    int uid, age;
    String name;
    double salary;
    String desig;
    double bonus = 50000;
 
    Emp(int id, String name, int age) {
    	
        uid =id;
        this.name = name;
        this.age = age;
    }
 
    public void display() {
        System.out.println("ID :" + uid);
        System.out.println("NAME :" + name);
        System.out.println("Age :" + age);
        System.out.println("Salary :" + salary);
        System.out.println("Designation :" + desig);
    }
 
    abstract void raiseSalary();
    abstract void Bonus();
}
 
class Clerk extends Emp {
	 Clerk(int uid, String name, int age) {
	        super(uid, name, age);
        salary = 25000;
        desig = "Clerk";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}
 
class Tester extends Emp {
	Tester(int uid, String name, int age) {
	        super(uid, name, age);
        salary = 40000;
        desig = "Tester";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}
 
class Developer extends Emp {
	Developer(int uid, String name, int age) {
        super(uid, name, age);
        salary = 70000;
        desig = "Developer";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}
 
class Manager extends Emp {
	Manager(int uid, String name, int age) {
        super(uid, name, age);
        salary = 90000;
        desig = "Manager";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}
 
public class crud {
    public static void main(String[] args) {
    	Connection con=null;
    	try {
        Scanner sc = new Scanner(System.in);
        int ch1 = 0, ch2 = 0;
        Emp e=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded....!");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
        System.out.println("Connection Established....!");
 
        do {
            try {
                System.out.println("\n1) Create ");
                System.out.println("2) Display ");
                System.out.println("3) Raise Salary ");
                System.out.println("4) Bonus ");
                System.out.println("5) Exit ");
                System.out.print("Enter your choice: ");
                ch1 = sc.nextInt();
 
                if (ch1 == 1) { //create
                    do {
                    	System.out.println("Choose desig for insertion:");
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        System.out.println("Enter choice:");
                        ch2 = sc.nextInt();
                        if(ch2!=5) {
                        System.out.println("Enter ID :");
                        int uid = sc.nextInt();
                        System.out.println("Enter Name :");
                        String name = sc.next();
                        System.out.println("Enter AGE :");
                        int age = sc.nextInt();
                        if(ch2==1) {e = new Clerk(uid, name,age); }
                        else if(ch2==2) {e = new Developer(uid, name,age);}
                        else if(ch2==3) { e = new Tester(uid, name,age);} 
                        else if(ch2==4) { e = new Manager(uid, name,age);} 
                        if(e!=null ) {
                        	  PreparedStatement insertStmt = con.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?, ?, ?)");
                        	  insertStmt.setInt(1, e.uid);
                              insertStmt.setString(2, e.name);
                              insertStmt.setInt(3, e.age);
                              insertStmt.setDouble(4, e.salary);
                              insertStmt.setString(5, e.desig);
                              insertStmt.execute();
                              insertStmt.close();
                              System.out.println("Data inserted successfully!");
                        }
                        }
                    } while (ch2 != 5);
                }
 
                if (ch1 == 2) { //display
                    do {
                    	System.out.println("Choose desig to display:");
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();
                        String desig="";
                        if(ch2!=5) {
                        try {
                            switch (ch2) {
                                case 1: desig="Clerk"; break;
                                case 2: desig="Developer"; break;
                                case 3: desig="Tester";break;
                                case 4: desig="Manager"; break;
                            }
                      	    PreparedStatement stmt = con.prepareStatement("select * from employee where desig= ?");
                      	    stmt.setString(1, desig);
                            ResultSet rs = stmt.executeQuery();
                            System.out.println("\nID | Name | Age | Salary | Designation\n");
                            System.out.println("------------------------------------------");
                            while (rs.next()) {
                                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " +rs.getInt(3) + " | " +rs.getDouble(4) + " | " + rs.getString(5));
                            }
                            rs.close();
                            stmt.close();
                            
                        } catch (NullPointerException ee) {
                            System.out.println("Error: " + ee.getMessage());
                        }
                        }
 
                    } while (ch2 != 5);
                }
 
                if (ch1 == 3) { //raise salary
                    do {
                    	System.out.println("Choose desig for salary raise:");
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();
 
                        try {
                            switch (ch2) {
                                case 1: if (e == null) throw new NullPointerException("Clerk not created yet!"); e.raiseSalary(); System.out.println("Salary Raised!"); break;
                                case 2: if (e == null) throw new NullPointerException("Developer not created yet!"); e.raiseSalary(); System.out.println("Salary Raised!"); break;
                                case 3: if (e == null) throw new NullPointerException("Tester not created yet!"); e.raiseSalary(); System.out.println("Salary Raised!"); break;
                                case 4: if (e == null) throw new NullPointerException("Manager not created yet!"); e.raiseSalary(); System.out.println("Salary Raised!"); break;
                            }
                            PreparedStatement updateStmt = con.prepareStatement("UPDATE Employee SET salary = ? WHERE desig = ?");

                        } catch (NullPointerException ee) {
                            System.out.println("Error: " + ee.getMessage());
                        }
 
                    } while (ch2 != 5);
                }
 
                if (ch1 == 4) {
                    do {
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();
 
//                        try {
//                            switch (ch2) {
//                                case 1: if (c == null) throw new NullPointerException("Clerk not created yet!"); c.Bonus(); System.out.println("Bonus Added!"); break;
//                                case 2: if (d == null) throw new NullPointerException("Developer not created yet!"); d.Bonus(); System.out.println("Bonus Added!"); break;
//                                case 3: if (t == null) throw new NullPointerException("Tester not created yet!"); t.Bonus(); System.out.println("Bonus Added!"); break;
//                                case 4: if (m == null) throw new NullPointerException("Manager not created yet!"); m.Bonus(); System.out.println("Bonus Added!"); break;
//                            }
//                        } catch (NullPointerException e) {
//                            System.out.println("Error: " + e.getMessage());
//                        }
 
                    } while (ch2 != 5);
                }
 
                if (ch1 == 5) {
                    System.out.println("Thank you.....!");
                    System.exit(0);
                }
 
            } catch (InputMismatchException me) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // clear the invalid input
            }
 
        } while (ch1 != 5);
 
        sc.close();
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}