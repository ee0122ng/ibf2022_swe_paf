package iss.ibf2022.pafday08workshop.exception;

public class CommentResourceNotFoundException extends RuntimeException {


    public CommentResourceNotFoundException() {
    }
    
    public CommentResourceNotFoundException(String message) {

        super(message);
    }
    
}
