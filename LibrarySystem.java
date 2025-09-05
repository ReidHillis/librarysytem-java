/**
 * Library Management System
 * 04/04/2025
 *
 * Main program to store borrow, return, and search books
 */

import java.io.*;
import java.util.Scanner;

public class LibrarySystem {
    private static final String BOOKS = "library.txt";

    public static void main(String[] args) {
        Library library = new Library();
        loadBooksFromFile(library);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // System actions for the user to choose from
            System.out.println("\nChoose an option (1-6):");
            System.out.println("1. Add a book");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Search for a book by ISBN");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Quit");

            // Getting the user's choice
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Adding book to system
            if (choice == 1) {
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter author name: ");
                String author = scanner.nextLine();
                System.out.print("Enter ISBN: ");
                String ID = scanner.nextLine();

                Book newBook = new Book(title, author, ID);
                library.addBook(newBook);
                saveBooksToFile(library);
                System.out.println("Book added successfully!");

            // Searching by title
            } else if (choice == 2) {
                System.out.print("Enter book title: ");
                String searchTitle = scanner.nextLine();

                long startTime = System.nanoTime(); // Start timing
                Book foundByTitle = library.findBookByTitle(searchTitle);
                long endTime = System.nanoTime(); // End timing
                System.out.println(foundByTitle != null ? "Found: " + foundByTitle : "Book not found.");
                System.out.println("Search time: " + (endTime - startTime) + " nanoseconds");

            // Searching by book ISBN
            } else if (choice == 3) {
                System.out.print("Enter ISBN: ");
                String searchID = scanner.nextLine();
                long startTime = System.nanoTime(); // Start timing
                Book foundByID = library.findBookByID(searchID);
                long endTime = System.nanoTime(); // End timing
                System.out.println(foundByID != null ? "Found: " + foundByID : "Book not found.");
                System.out.println("Search time: " + (endTime - startTime) + " nanoseconds");

            // Borrowing a book
            } else if (choice == 4) {
                System.out.print("Enter book title to borrow: ");
                String borrowTitle = scanner.nextLine();
                long startTime = System.nanoTime(); // Start timing
                Book bookToBorrow = library.findBookByTitle(borrowTitle);
                if (bookToBorrow != null && bookToBorrow.isAvailable) {
                    bookToBorrow.borrowBook();
                    saveBooksToFile(library);
                    long endTime = System.nanoTime(); // End timing
                    System.out.println("Search time: " + (endTime - startTime) + " nanoseconds");
                } else {
                    System.out.println("Book is unavailable or not found.");
                }

            // Returning a book
            } else if (choice == 5) {
                System.out.print("Enter book title to return: ");
                String returnTitle = scanner.nextLine();

                long startTime = System.nanoTime(); // Start timing
                Book bookToReturn = library.findBookByTitle(returnTitle);
                if (bookToReturn != null && !bookToReturn.isAvailable) {
                    bookToReturn.returnBook();
                    saveBooksToFile(library);
                    long endTime = System.nanoTime(); // End timing
                    System.out.println("Search time: " + (endTime - startTime) + " nanoseconds");
                } else {
                    System.out.println("Book is not borrowed or not found.");
                }
            // Quitting the system
            } else if (choice == 6) {
                saveBooksToFile(library);
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    // Writes books in the library to a file
    private static void saveBooksToFile(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS))) {
            // Loops through all books and writes each book's details
            for (Book book : library.getAllBooks()) {
                writer.println(book.title + "," + book.author + "," + book.ID + "," + book.isAvailable);
            }
        } catch (IOException e) { // If an error occurs
            System.out.println("Error saving book data.");
        }
    }

    // Reads books in the file into the library
    private static void loadBooksFromFile(Library library) {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS))) {
            String line;
            // Reads each line in file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Splits each line into an array
                if (data.length == 4) {
                    Book book = new Book(data[0], data[1], data[2]); // Creates new Book object with books details
                    book.isAvailable = Boolean.parseBoolean(data[3]); // Sets availability
                    library.addBook(book); // Adds book to library
                }
            }
        } catch (IOException e) { // If an error occurs
            System.out.println("No book data found.");
        }
    }
}

