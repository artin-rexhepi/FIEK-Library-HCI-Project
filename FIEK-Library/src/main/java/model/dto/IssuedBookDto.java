package model.dto;

import java.sql.Timestamp;

public class IssuedBookDto {
    private String isbn;
    private String memberID;
    private Timestamp issueTime;
    private int renewCount;

    public IssuedBookDto() {
    }

    public IssuedBookDto(String isbn, String memberID, Timestamp issueTime, int renewCount) {
        this.isbn = isbn;
        this.memberID = memberID;
        this.issueTime = issueTime;
        this.renewCount = renewCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public Timestamp getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Timestamp issueTime) {
        this.issueTime = issueTime;
    }

    public int getRenewCount() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }
}
