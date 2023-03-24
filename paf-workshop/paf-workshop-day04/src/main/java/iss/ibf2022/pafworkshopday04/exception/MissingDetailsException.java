package iss.ibf2022.pafworkshopday04.exception;

public class MissingDetailsException extends RuntimeException {

    public MissingDetailsException() {
        super();
    }

    public MissingDetailsException(String message) {
        super(message);
    }

    public MissingDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
