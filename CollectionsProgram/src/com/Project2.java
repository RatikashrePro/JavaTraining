//Employee Payroll - Find Highest & Lowest Salary

package com;

import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class Project2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<Employee>();

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        // Input employee data
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            employees.add(new Employee(id, name, salary));
        }

        // Display all employees
        System.out.println("\n---- Employee Payroll ----");
        for (Employee e : employees) {
            System.out.println(e);
        }

        // Find highest and lowest salary
        if (!employees.isEmpty()) {
            Employee highest = employees.get(0);
            Employee lowest = employees.get(0);

            for (Employee e : employees) {
                if (e.salary > highest.salary) {
                    highest = e;
                }
                if (e.salary < lowest.salary) {
                    lowest = e;
                }
            }

            System.out.println("\n💰 Highest Salary: " + highest);
            System.out.println("💸 Lowest Salary: " + lowest);
        }
        
        sc.close();
    }
}
