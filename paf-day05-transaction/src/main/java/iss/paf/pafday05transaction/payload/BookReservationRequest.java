package iss.paf.pafday05transaction.payload;

import java.util.List;

import iss.paf.pafday05transaction.model.Book;

public class BookReservationRequest {

    private List<Book> books;

    private String fullName;

    private String message;
    
    public BookReservationRequest() {
    }

    public BookReservationRequest(List<Book> books, String fullName) {
        this.books = books;
        this.fullName = fullName;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
