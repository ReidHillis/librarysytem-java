/**
 * Library Management System
 * 04/04/2025
 *
 * Binary search tree for finding book by title
 */

public class BookSearchTree {
    private SearchTreeNode root;

    // Inserts book into the binary search tree
    public void insert(Book book) {
        root = insertRecursively(root, book);
    }

    // Method to insert book recursively in sorted order
    private SearchTreeNode insertRecursively(SearchTreeNode root, Book book) {
        if (root == null){
            return new SearchTreeNode(book);
        }
        if (book.title.compareTo(root.book.title) < 0) {
            root.left = insertRecursively(root.left, book); // Insert in left subtree
        } else {
            root.right = insertRecursively(root.right, book); // Insert in right subtree
        }
        return root;
    }

    // Searches for book by title
    public Book search(String title) {
        return searchRecursively(root, title);
    }

    // Method for recursive search
    private Book searchRecursively(SearchTreeNode root, String title) {
        if (root == null) {
            return null; // Book not found
        }
        if (title.equals(root.book.title)) {
            return root.book; // Book found
        }
        if (title.compareTo(root.book.title) < 0) {
            return searchRecursively(root.left, title); // Search left subtree
        } else {
            return searchRecursively(root.right, title); // Search right subtree
        }
    }
}
