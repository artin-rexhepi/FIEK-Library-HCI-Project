package service;

import model.dto.BookDTO;
import repository.BookRepository;
import java.time.LocalDate;

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

    private boolean isValidBook(BookDTO bookDTO) {
        // Perform validation on book data
        if (bookDTO.getISBN().isEmpty() || bookDTO.getTitle().isEmpty() || bookDTO.getPublisher().isEmpty()
                || bookDTO.getPublicationDate() == null || bookDTO.getNumberOfPages() <= 0 || bookDTO.getAuthorId() <= 0) {
            return false;
        }

        // Additional validation logic can be added here

        // Example of additional validation: Publication date should not be in the future
        if (bookDTO.getPublicationDate().isAfter(LocalDate.now())) {
            return false;
        }

        return true;
    }
}
