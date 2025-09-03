package com;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UpdateEmployee {
    public static void main(String[] args) {
        // Load config and build SessionFactory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Scanner sc= new Scanner(System.in);
            int empId = sc.nextInt(); // Example ID
            Employee emp = session.get(Employee.class, empId);

            if (emp != null) {
                System.out.println("Before Update -> Salary: " + emp.getSalary());

                // Update values
                System.out.println("Enter updates");
                emp.setSalary(emp.getSalary() + 5000);
                emp.setDesig("Senior " + emp.getDesig());

                // Save update
                session.update(emp);

                tx.commit();
                System.out.println("Employee Updated Successfully!");
            } else {
                System.out.println("Employee not found with ID " + empId);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
