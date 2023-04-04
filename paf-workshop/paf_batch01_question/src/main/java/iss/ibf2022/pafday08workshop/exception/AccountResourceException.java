package iss.ibf2022.pafday08workshop.exception;

public class AccountResourceException extends RuntimeException {
    
    public AccountResourceException() {

    }

    public AccountResourceException(String message) {
        super(message);
    }
}
