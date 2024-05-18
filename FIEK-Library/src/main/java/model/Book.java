package model;

public class Book {
    private String ISBN;
    private String title;
    private String subject;
    private String publisher;
    private String quantity;
    private String author;

    // Constructors
    public Book(String ISBN, String title, String genre, String publisher, String s, String author, boolean isAvailable) {
        // Default constructor
    }

    public Book(String ISBN, String title, String subject, String publisher, String quantity, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.quantity = quantity;
        this.author = author;
    }

    // Getters and setters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
