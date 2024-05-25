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
        // Check for empty fields
        if (bookDTO.getISBN().isEmpty() || bookDTO.getTitle().isEmpty() || bookDTO.getPublisher().isEmpty()
                || bookDTO.getQuantity().isEmpty() || bookDTO.getAuthor().isEmpty()) { // Updated to match BookDTO
            return false;
        }

        // Validate ISBN format (assuming ISBN-10 or ISBN-13)
        if (!isValidISBN(bookDTO.getISBN())) {
            return false;
        }

        // Validate title length
        if (bookDTO.getTitle().length() < 2 || bookDTO.getTitle().length() > 200) {
            return false;
        }

        // Validate publisher length
        if (bookDTO.getPublisher().length() < 2 || bookDTO.getPublisher().length() > 200) {
            return false;
        }

        // Validate author length
        if (bookDTO.getAuthor().length() < 2 || bookDTO.getAuthor().length() > 200) {
            return false;
        }

        // Validate quantity
        try {
            int quantity = Integer.parseInt(bookDTO.getQuantity());
            if (quantity <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false; // Quantity is not a valid integer
        }

        // Additional validation logic can be added here

        return true;
    }

    private boolean isValidISBN(String isbn) {
        if (isbn == null) {
            return false;
        }
        isbn = isbn.replaceAll("-", "");
        int length = isbn.length();
        if (length == 10) {
            return isValidISBN10(isbn);
        } else if (length == 13) {
            return isValidISBN13(isbn);
        } else {
            return false;
        }
    }

    private boolean isValidISBN10(String isbn) {
        if (isbn == null || isbn.length() != 10) {
            return false;
        }
        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                sum += (digit * (10 - i));
            }
            String checksum = Integer.toString((11 - (sum % 11)) % 11);
            if ("10".equals(checksum)) {
                checksum = "X";
            }
            return checksum.equals(isbn.substring(9));
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isValidISBN13(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            return false;
        }
        try {
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                sum += (i % 2 == 0) ? digit : (digit * 3);
            }
            int checksum = 10 - (sum % 10);
            if (checksum == 10) {
                checksum = 0;
            }
            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
