package iss.ibf2022.pafday06workshop.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import iss.ibf2022.pafday06workshop.exception.ResourceNotFoundException;
import iss.ibf2022.pafday06workshop.payload.ErrorPayload;

@RestControllerAdvice
public class ErrorController {

    private String NOT_FOUND = "NOT FOUND";

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorPayload> handleResourceNotFoundException(ResourceNotFoundException ex) {
        
        String details = ex.getLocalizedMessage();
        ErrorPayload payload = new ErrorPayload(NOT_FOUND, details);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payload);
    }
    
}
