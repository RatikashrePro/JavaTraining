package com;
 
import java.io.*;
import java.util.*;
 
public class file {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Enter employee file name (with extension): ");
        String fileName = sc.nextLine();
        File file = new File(fileName);
 
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("New file created: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
 
        while (true) {
            System.out.println("\n===== Employee CRUD Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
 
            switch (choice) {
                case 1: addEmployee(file, sc); break;
                case 2: displayEmployees(file); break;
                case 3: updateEmployee(file, sc); break;
                case 4: deleteEmployee(file, sc); break;
                case 5: System.out.println("Exiting..."); sc.close(); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
 
    // CREATE
    private static void addEmployee(File file, Scanner sc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            System.out.print("Enter Employee ID: ");
            String id = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Age: ");
            String age = sc.nextLine();
            System.out.print("Enter Salary: ");
            String salary = sc.nextLine();
            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();
 
            String record = id + "," + name + "," + age + "," + salary + "," + designation;
 
            bw.write(record);
            bw.newLine();
 
            System.out.println("Employee added successfully!");
 
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
 
    // READ / DISPLAY
    private static void displayEmployees(File file) {
        if (!file.exists() || file.length() == 0) {
            System.out.println("No employees found!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("---- Employee Records ----");
            System.out.printf("%-5s %-15s %-5s %-10s %-15s%n", "ID", "Name", "Age", "Salary", "Designation");
            System.out.println("-------------------------------------------------------------");
 
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    System.out.printf("%-5s %-15s %-5s %-10s %-15s%n",
                            parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
 
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
 
    // UPDATE
    private static void updateEmployee(File file, Scanner sc) {
        System.out.print("Enter Employee ID to update: ");
        String empId = sc.nextLine();
        List<String> lines = new ArrayList<>();
        boolean found = false;
 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(empId)) {
                    found = true;
                    System.out.println("Enter new details:");
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Age: "); String age = sc.nextLine();
                    System.out.print("Salary: "); String salary = sc.nextLine();
                    System.out.print("Designation: "); String designation = sc.nextLine();
                    line = empId + "," + name + "," + age + "," + salary + "," + designation;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
 
        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
                System.out.println("Employee updated successfully!");
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
        } else {
            System.out.println("Employee ID not found!");
        }
    }
 
    // DELETE
    private static void deleteEmployee(File file, Scanner sc) {
        System.out.print("Enter Employee ID to delete: ");
        String empId = sc.nextLine();
        List<String> lines = new ArrayList<>();
        boolean found = false;
 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(empId)) {
                    found = true; // skip this line (delete)
                    continue;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
 
        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
                System.out.println("Employee deleted successfully!");
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
        } else {
            System.out.println("Employee ID not found!");
        }
    }
}