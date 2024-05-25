package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import model.dto.IssuedBookDto;
import repository.BookRepository;
import repository.IssuedBooksRepository;

import java.util.List;

public class IssuedBooksService {

    private final IssuedBooksRepository issuedBooksRepository;
    private final BookRepository bookRepository;

    public IssuedBooksService() {
        this.issuedBooksRepository = new IssuedBooksRepository();
        this.bookRepository = new BookRepository();
    }

    public boolean issueBook(IssuedBookDto issuedBookDto) {
        // Validate issued book data
        if (!isValidIssuedBook(issuedBookDto)) {
            return false;
        }

        // Check if the book quantity is greater than 0
        if (!isBookAvailable(issuedBookDto.getIsbn())) {
            return false;
        }

        // Check if the book-member combination is unique
        if (!isUniqueIssuedBook(issuedBookDto.getIsbn(), issuedBookDto.getMemberID())) {
            return false;
        }

        // Decrement the book quantity
        if (!issuedBooksRepository.decrementBookQuantity(issuedBookDto.getIsbn())) {
            return false;  // Failed to decrement, possibly due to no stock
        }

        // Call method to save issued book to the database
        return issuedBooksRepository.create(issuedBookDto);
    }

    private boolean isValidIssuedBook(IssuedBookDto issuedBookDto) {
        // Perform validation on issued book data
        if (issuedBookDto.getIsbn().isEmpty() || issuedBookDto.getMemberID().isEmpty()
                || issuedBookDto.getIssueTime() == null) {
            return false;
        }

        // Additional validation logic can be added here

        return true;
    }

    private boolean isBookAvailable(String isbn) {
        int quantity = bookRepository.getBookQuantity(isbn);
        return quantity > 0;
    }

    private boolean isUniqueIssuedBook(String isbn, String memberID) {
        List<IssuedBookDto> existingIssuedBooks = issuedBooksRepository.getAllIssuedBooks();
        return existingIssuedBooks.stream().noneMatch(book -> book.getIsbn().equals(isbn) && book.getMemberID().equals(memberID));
    }

    public boolean returnBook(IssuedBookDto issuedBookDto) {
        // Increment the book quantity
        if (!issuedBooksRepository.incrementBookQuantity(issuedBookDto.getIsbn())) {
            return false;  // Failed to increment quantity
        }

        // Reset the renew count
        if (!issuedBooksRepository.resetRenewCount(issuedBookDto.getIsbn(), issuedBookDto.getMemberID())) {
            return false;  // Failed to reset renew count
        }

        return true;  // Successfully returned the book
    }

    public PieChart generatePieChart() {
        // Fetch all issued books
        List<IssuedBookDto> issuedBooks = issuedBooksRepository.getAllIssuedBooks();

        // Count the number of borrowed and returned books
        int borrowedCount = 0;
        int returnedCount = 0;
        for (IssuedBookDto issuedBook : issuedBooks) {
            int renewCount = issuedBooksRepository.getRenewCount(issuedBook.getIsbn(), issuedBook.getMemberID());
            if (renewCount > 0) {
                borrowedCount++;
            } else {
                returnedCount++;
            }
        }

        // Create the PieChart
        PieChart.Data borrowedData = new PieChart.Data("Borrowed: " + borrowedCount, borrowedCount);
        PieChart.Data returnedData = new PieChart.Data("Returned: " + returnedCount, returnedCount);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(borrowedData, returnedData);
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Books Status");
        return pieChart;
    }

    public List<IssuedBookDto> getAllIssuedBooks() {
        return issuedBooksRepository.getAllIssuedBooks();
    }
}
