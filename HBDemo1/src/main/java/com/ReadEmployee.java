package com;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployee {
    public static void main(String[] args) {
        // Load config and build SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        try {
            // Fetch employee using get()
        	Scanner sc= new Scanner(System.in);
        	
            int empId = sc.nextInt(); // Example ID
            Employee emp = session.get(Employee.class, empId);

            if (emp != null) {
                System.out.println("Employee Found:");
                System.out.println("ID: " + emp.getId());
                System.out.println("Name: " + emp.getName());
                System.out.println("Age: " + emp.getAge());
                System.out.println("Salary: " + emp.getSalary());
                System.out.println("Designation: " + emp.getDesig());
            } else {
                System.out.println("Employee not found with ID " + empId);
            }
        } finally {
            session.close();
            factory.close();
        }
    }
}
