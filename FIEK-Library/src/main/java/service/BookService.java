package service;

import model.dto.BookDTO;
import repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public boolean registerBook(BookDTO bookDTO) {
        // Validate book data
        if (!isValidBook(bookDTO)) {
            return false;
        }

        // Call method to save book to database
        return bookRepository.create(bookDTO);
    }

    public boolean deleteBook(BookDTO bookDTO) {
        // Call method to delete book from database
        return bookRepository.delete(bookDTO);
    }

    private boolean isValidBook(BookDTO bookDTO) {
        // Perform validation on book data
        if (bookDTO.getISBN().isEmpty() || bookDTO.getTitle().isEmpty() || bookDTO.getPublisher().isEmpty()
                || bookDTO.getQuantity().isEmpty() || bookDTO.getAuthor().isEmpty()) { // Updated to match BookDTO
            return false;
        }

        // Additional validation logic can be added here

        // Example of additional validation: Quantity should be a positive integer
        try {
            int quantity = Integer.parseInt(bookDTO.getQuantity());
            if (quantity <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false; // Quantity is not a valid integer
        }

        return true;
    }
}
