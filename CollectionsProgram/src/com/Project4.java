//Library Management system - Check book availability

package com;

import java.util.*;

class Book {
    int id;
    String title;
    String author;
    int copies;   // number of copies available

    Book(int id, String title, String author, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Copies Available: " + copies;
    }
}

public class Project4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> library = new ArrayList<Book>();

        // Pre-added books
        library.add(new Book(101, "Java Programming", "James Gosling", 3));
        library.add(new Book(102, "Data Structures", "Robert Lafore", 2));
        library.add(new Book(103, "Database Systems", "Ramakrishnan", 0));

        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Display All Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Book ID: ");
                int id = sc.nextInt();
                sc.nextLine(); // consume newline
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                System.out.print("Enter Author: ");
                String author = sc.nextLine();
                System.out.print("Enter Number of Copies: ");
                int copies = sc.nextInt();

                library.add(new Book(id, title, author, copies));
                System.out.println("✅ Book added successfully!");
            }
            else if (choice == 2) {
                System.out.print("Enter Book ID to search: ");
                int searchId = sc.nextInt();
                boolean found = false;

                for (Book b : library) {
                    if (b.id == searchId) {
                        System.out.println("📖 Book Found: " + b);
                        if (b.copies > 0) {
                            System.out.println("✅ The book is available for issue (" + b.copies + " copies left).");
                        } else {
                            System.out.println("❌ Sorry, all copies are already issued.");
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("⚠️ Book with ID " + searchId + " not found.");
                }
            }
            else if (choice == 3) {
                System.out.println("\n--- All Books in Library ---");
                if (library.isEmpty()) {
                    System.out.println("No books available.");
                } else {
                    for (Book b : library) {
                        System.out.println(b);
                    }
                }
            }

        } while (choice != 4);

        sc.close();
    }
}
