/**
 * Library Management System
 * 04/04/2025
 *
 * Node in the binrary search tree for book storage
 */

public class SearchTreeNode {
    Book book; // Book stored in this node
    SearchTreeNode left, right; // Book's left and right children

    public SearchTreeNode(Book book) {
        this.book = book;
        this.left = this.right = null; // No children to begin with
    }
}
