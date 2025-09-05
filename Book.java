/**
 * Library Management System
 * 04/04/2025
 *
 * Holds books with title, author, ID, and availability
 */

public class Book {
    String title, author, ID;
    boolean isAvailable;

    // Constructor to intialize a book with a title, author, and ID
    public Book(String title, String author, String ID) {
        this.title = title;
        this.author = author;
        this.ID = ID;
        this.isAvailable = true; // If you make a new book, it is available at first automatically
    }

    // Method to borrow books
    public void borrowBook() {
        // If it is available to borrow, it will allow you and change availability
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        // If it is not available to borrow, system will let the user know
        } else {
            System.out.println(title + " is currently unavailable.");
        }
    }

    // Method to return a book previously borrowed
    public void returnBook() {
        isAvailable = true;
        System.out.println(title + " has been returned.");
    }

    // Converts book details to string
    public String toString() {
        String availability;
        if (isAvailable) {
            availability = "Available";
        } else {
            availability = "Not Available";
        }
        return "Title: " + title + ", Author: " + author + ", ID: " + ID + ", Status: " + availability;
    }
}
