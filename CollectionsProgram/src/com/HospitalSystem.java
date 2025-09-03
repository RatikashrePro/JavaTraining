package com;

import java.util.ArrayList;
import java.util.Scanner;

class Patient {

    int id;
    String name;
    int age;
    String disease;
    boolean critical;
    boolean currentStatus; // true = admitted, false = discharged

    Patient(int id, String name, int age, String disease, boolean critical, boolean currentStatus) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.critical = critical;
        this.currentStatus = currentStatus;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age +
               ", Disease: " + disease + ", Critical: " + critical +
               ", Status: " + (currentStatus ? "Admitted" : "Discharged");
    }
}

public class HospitalSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Patient> patients = new ArrayList<Patient>();

        while (true) {
            System.out.println("\n==== HOSPITAL MANAGEMENT SYSTEM ====");
            System.out.println("1. Add Patient");
            System.out.println("2. Search Patient by ID");
            System.out.println("3. Display All Patients");
            System.out.println("4. Filter Critical Patients");
            System.out.println("5. Update Patient Details");
            System.out.println("6. Delete Patient Record");
            System.out.println("7. Count Patients by Disease");
            System.out.println("8. Display Patients Above Certain Age");
            System.out.println("9. Discharge Patient");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if (choice == 1) { // Add Patient
                System.out.print("Enter Patient ID: ");
                int id = sc.nextInt();
                System.out.print("Enter Name: ");
                String name = sc.next();
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                System.out.print("Enter Disease: ");
                String disease = sc.next();
                System.out.print("Is Critical (true/false): ");
                boolean critical = sc.nextBoolean();

                // when adding a new patient → they are admitted
                patients.add(new Patient(id, name, age, disease, critical, true));
                System.out.println("✅ Patient admitted successfully!");
            }

            else if (choice == 2) { // Search by ID
                System.out.print("Enter Patient ID to search: ");
                int searchId = sc.nextInt();
                boolean found = false;
                for (Patient p : patients) {
                    if (p.id == searchId) {
                        System.out.println("🔎 Patient Found: " + p);
                        found = true;
                        break;
                    }
                }
                if (!found)
                    System.out.println("❌ Patient not found.");
            }

            else if (choice == 3) { // Display All
                if (patients.isEmpty())
                    System.out.println("No patients found.");
                else {
                    System.out.println("--- All Patients ---");
                    for (Patient p : patients)
                        System.out.println(p);
                }
            }

            else if (choice == 4) { // Critical filter
                System.out.println("--- Critical Patients (Admitted) ---");
                boolean anyCritical = false;
                for (Patient p : patients) {
                    if (p.critical && p.currentStatus) {
                        System.out.println(p);
                        anyCritical = true;
                    }
                }
                if (!anyCritical)
                    System.out.println("No critical patients admitted.");
            }

            else if (choice == 5) { // Update patient
                System.out.print("Enter Patient ID to update: ");
                int updateId = sc.nextInt();
                boolean updated = false;
                for (Patient p : patients) {
                    if (p.id == updateId) {
                        System.out.print("Enter new Name: ");
                        p.name = sc.next();
                        System.out.print("Enter new Age: ");
                        p.age = sc.nextInt();
                        System.out.print("Enter new Disease: ");
                        p.disease = sc.next();
                        System.out.print("Is Critical (true/false): ");
                        p.critical = sc.nextBoolean();
                        System.out.print("Is Patient still Admitted (true/false): ");
                        p.currentStatus = sc.nextBoolean();
                        System.out.println("✅ Patient updated: " + p);
                        updated = true;
                        break;
                    }
                }
                if (!updated)
                    System.out.println("❌ Patient not found.");
            }

            else if (choice == 6) { // Delete patient (rare in real life)
                System.out.print("Enter Patient ID to delete record: ");
                int deleteId = sc.nextInt();
                boolean removed = false;
                for (int i = 0; i < patients.size(); i++) {
                    if (patients.get(i).id == deleteId) {
                        System.out.println("🗑️ Patient record deleted: " + patients.get(i));
                        patients.remove(i);
                        removed = true;
                        break;
                    }
                }
                if (!removed)
                    System.out.println("❌ Patient not found.");
            }

            else if (choice == 7) { // Count by disease
                System.out.print("Enter Disease to count patients: ");
                String d = sc.next();
                int count = 0;
                for (Patient p : patients)
                    if (p.disease.equalsIgnoreCase(d) && p.currentStatus)
                        count++;
                System.out.println("Patients admitted with " + d + ": " + count);
            }

            else if (choice == 8) { // Patients above age
                System.out.print("Enter minimum age: ");
                int minAge = sc.nextInt();
                System.out.println("--- Patients Above Age " + minAge + " ---");
                boolean any = false;
                for (Patient p : patients) {
                    if (p.age > minAge && p.currentStatus) {
                        System.out.println(p);
                        any = true;
                    }
                }
                if (!any)
                    System.out.println("No admitted patients above this age.");
            }

            else if (choice == 9) { // Discharge patient (just change status)
                System.out.print("Enter Patient ID to discharge: ");
                int dischargeId = sc.nextInt();
                boolean discharged = false;
                for (Patient p : patients) {
                    if (p.id == dischargeId) {
                        if (!p.currentStatus) {
                            System.out.println("⚠️ Patient is already discharged.");
                        } else {
                            p.currentStatus = false;
                            System.out.println("🏥 Patient discharged: " + p);
                        }
                        discharged = true;
                        break;
                    }
                }
                if (!discharged)
                    System.out.println("❌ Patient not found.");
            }

            else if (choice == 10) {
                System.out.println("👋 Exiting Hospital System...");
                break;
            }

            else {
                System.out.println("⚠️ Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}
