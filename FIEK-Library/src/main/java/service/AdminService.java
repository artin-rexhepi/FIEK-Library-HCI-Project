package service;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import model.Member;
import model.dto.MemberDto;
import repository.AdminRepository;

public class AdminService {
    public ObservableList<MemberDto> getMemberData() {
        return AdminRepository.getMemberData();
    }

    public void setMembers(TableColumn<MemberDto, String> column, String columnName) {
        AdminRepository.setMembers(column, columnName);
    }

}
