package model.filter;

public class BookFilter extends Filter {
    private String isbn;
    private String title;
    private String subject;
    private String publisher;
    private String author;

    public BookFilter(String isbn, String title, String author, String publisher, String subject) {
        this.isbn = isbn;
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.author = author;
    }

    public String buildQuery() {
        StringBuilder query = new StringBuilder();

        if (this.isbn != null && !this.isbn.isEmpty()) {
            query.append(" AND isbn LIKE '").append(this.isbn).append("%' ");
        }

        if (this.title != null && !this.title.isEmpty()) {
            query.append(" AND title LIKE '").append(this.title).append("%' ");
        }

        if (this.subject != null && !this.subject.isEmpty()) {
            query.append(" AND subject LIKE '").append(this.subject).append("%' ");
        }

        if (this.publisher != null && !this.publisher.isEmpty()) {
            query.append(" AND publisher LIKE '").append(this.publisher).append("%' ");
        }


        if (this.author != null && !this.author.isEmpty()) {
            query.append(" AND author LIKE '").append(this.author).append("%' ");
        }

        return query.toString();
    }
}
