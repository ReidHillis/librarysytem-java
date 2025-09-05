/**
 * Library Management System
 * 04/04/2025
 *
 * Adds books and uses HashMap for ID or BST for title lookup
 */

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


class Library {
    private HashMap<String, Book> bookHashMap = new HashMap<>();
    private BookSearchTree bookBST = new BookSearchTree();

    // Adds a book to both HashMap and binary search tree
    public void addBook(Book book) {
        bookHashMap.put(book.ID, book);
        bookBST.insert(book);
    }

    // Finds book by ID using HashMap lookup
    public Book findBookByID(String ID) {
        return bookHashMap.get(ID);
    }

    // Finds book by title using BST search
    public Book findBookByTitle(String title) {
        return bookBST.search(title);
    }

    // Returns list of books stored in HashMap
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookHashMap.values());
    }
}
