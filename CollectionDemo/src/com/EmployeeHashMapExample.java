package com;

import java.util.HashMap;

class Employee {
    int id;
    String name;
    String designation;
    double salary;
    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return id + " - " + name + " (" + designation + "), Salary: " + salary;
    }
}
public class EmployeeHashMapExample {
    public static void main(String[] args) {
        // Employee ID -> Employee Object
        HashMap<Integer, Employee> empMap = new HashMap<>();
        // Add employees
        empMap.put(101, new Employee(101, "Abi", "Developer", 50000));
        empMap.put(102, new Employee(102, "Bharath", "Tester", 40000));
        empMap.put(103, new Employee(103, "Charan", "Manager", 70000));
        // Fetch by ID
        System.out.println("Employee with ID 102: " + empMap.get(102));
        // Remove an employee
        empMap.remove(101);
        // Iterate
        System.out.println("\nAll Employees:");
        for (Integer empId : empMap.keySet()) {
            System.out.println(empMap.get(empId));
        }
    }
}