package iss.paf.pafday05transaction.exception;

public class BookException extends RuntimeException {

    public BookException() {
        super();
    }

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
