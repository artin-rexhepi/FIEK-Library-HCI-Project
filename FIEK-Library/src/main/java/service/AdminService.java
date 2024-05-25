package service;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.control.TableColumn;
import model.Member;
import model.dto.BookDTO;
import model.dto.MemberDto;
import model.filter.BookFilter;
import model.filter.MemberFilter;
import repository.AdminRepository;

public class AdminService {
    public ObservableList<MemberDto> getMemberData() {
        return AdminRepository.getMemberData();
    }

    public ObservableList<BookDTO> getBookData() {
        return AdminRepository.getBookData();
    }

    public void setMembers(TableColumn<MemberDto, String> column, String columnName) {
        AdminRepository.setMembers(column, columnName);
    }

    public void setBooks(TableColumn<BookDTO, String> column, String columnName) {
        AdminRepository.setBooks(column, columnName);
    }

    public ObservableList<MemberDto> getIssuedBookMember(){
        return AdminRepository.getIssuedBookMember();
    }
    public ObservableList<BookDTO> getIssuedBooks() { return AdminRepository.getIssuedBooks(); }

    public ObservableList<MemberDto> filter(MemberFilter memberFilter){
        return AdminRepository.getByFilter(memberFilter);
    }

    public ObservableList<BookDTO> getAvailableBooks() { return AdminRepository.getAvailableBooks(); }

    public ObservableList<BookDTO> getBookByFilter(BookFilter bookFilter) {
        return AdminRepository.getBookByFilter(bookFilter);
    }
}
