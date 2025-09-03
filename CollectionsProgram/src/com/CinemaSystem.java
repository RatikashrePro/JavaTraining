package com;

import java.util.ArrayList;
import java.util.Scanner;

class Movie {
    int id;
    String title;
    String showTime;
    double ticketPrice;
    int availableSeats;

    Movie(int id, String title, String showTime, double ticketPrice, int availableSeats) {
        this.id = id;
        this.title = title;
        this.showTime = showTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "🎬 ID: " + id + ", Title: " + title + ", ShowTime: " + showTime +
               ", Price: ₹" + ticketPrice + ", Available Seats: " + availableSeats;
    }
}

public class CinemaSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> movies = new ArrayList<>();

        // Pre-added movies
        movies.add(new Movie(1, "Inception", "7:00 PM", 250, 50));
        movies.add(new Movie(2, "Avengers: Endgame", "9:30 PM", 300, 100));
        movies.add(new Movie(3, "The Dark Knight", "6:00 PM", 200, 75));

        while (true) {
            System.out.println("\n===== CINEMA TICKET BOOKING SYSTEM =====");
            System.out.println("1. Add Movie");
            System.out.println("2. Display All Movies");
            System.out.println("3. Search Movie by ID");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Check Availability");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Movie ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                System.out.print("Enter Show Time: ");
                String showTime = sc.nextLine();
                System.out.print("Enter Ticket Price: ");
                double price = sc.nextDouble();
                System.out.print("Enter Available Seats: ");
                int seats = sc.nextInt();

                movies.add(new Movie(id, title, showTime, price, seats));
                System.out.println("✅ Movie added successfully!");
            }
            else if (choice == 2) {
                if (movies.isEmpty()) {
                    System.out.println("No movies available.");
                } else {
                    System.out.println("--- All Movies ---");
                    for (Movie m : movies) {
                        System.out.println(m);
                    }
                }
            }
            else if (choice == 3) {
                System.out.print("Enter Movie ID to search: ");
                int id = sc.nextInt();
                boolean found = false;
                for (Movie m : movies) {
                    if (m.id == id) {
                        System.out.println("🎥 Movie Found: " + m);
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("❌ Movie not found.");
            }
            else if (choice == 4) { // Book Ticket
                System.out.print("Enter Movie ID: ");
                int id = sc.nextInt();
                System.out.print("Enter number of tickets: ");
                int tickets = sc.nextInt();
                boolean booked = false;
                for (Movie m : movies) {
                    if (m.id == id) {
                        if (m.availableSeats >= tickets) {
                            m.availableSeats -= tickets;
                            System.out.println("✅ Successfully booked " + tickets + " tickets for " + m.title);
                            System.out.println("Total Price: ₹" + (tickets * m.ticketPrice));
                        } else {
                            System.out.println("⚠️ Not enough seats available!");
                        }
                        booked = true;
                        break;
                    }
                }
                if (!booked) System.out.println("❌ Movie not found.");
            }
            else if (choice == 5) { // Cancel Ticket
                System.out.print("Enter Movie ID: ");
                int id = sc.nextInt();
                System.out.print("Enter number of tickets to cancel: ");
                int cancel = sc.nextInt();
                boolean canceled = false;
                for (Movie m : movies) {
                    if (m.id == id) {
                        m.availableSeats += cancel;
                        System.out.println("✅ Successfully canceled " + cancel + " tickets for " + m.title);
                        canceled = true;
                        break;
                    }
                }
                if (!canceled) System.out.println("❌ Movie not found.");
            }
            else if (choice == 6) { // Check availability
                System.out.print("Enter Movie ID: ");
                int id = sc.nextInt();
                boolean found = false;
                for (Movie m : movies) {
                    if (m.id == id) {
                        System.out.println("🎟️ Available Seats for " + m.title + ": " + m.availableSeats);
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("❌ Movie not found.");
            }
            else if (choice == 7) {
                System.out.println("👋 Exiting Cinema Booking System...");
                break;
            }
            else {
                System.out.println("⚠️ Invalid choice. Try again!");
            }
        }
        sc.close();
    }
}
