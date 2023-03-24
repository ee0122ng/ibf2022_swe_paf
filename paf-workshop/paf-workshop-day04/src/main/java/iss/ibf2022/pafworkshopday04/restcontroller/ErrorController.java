package iss.ibf2022.pafworkshopday04.restcontroller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import iss.ibf2022.pafworkshopday04.exception.MissingDetailsException;
import iss.ibf2022.pafworkshopday04.payload.ErrorPayload;

@RestControllerAdvice
public class ErrorController {

    private final String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler({MissingDetailsException.class})
    public ResponseEntity<ErrorPayload> handleMissingDetailsException(MissingDetailsException ex, WebRequest req){

        List<String> details = new LinkedList<>();
        details.add(ex.getLocalizedMessage());
        ErrorPayload payload = new ErrorPayload(BAD_REQUEST, details);

        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        
        
    }
    
}
