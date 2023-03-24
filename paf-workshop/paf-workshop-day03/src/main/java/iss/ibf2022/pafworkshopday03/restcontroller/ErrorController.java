package iss.ibf2022.pafworkshopday03.restcontroller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import iss.ibf2022.pafworkshopday03.exception.ResourceNotFoundException;
import iss.ibf2022.pafworkshopday03.payload.ErrorMessage;

@RestControllerAdvice
public class ErrorController {

    private String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseBody()
    public final ResponseEntity<ErrorMessage> handleOrderNotFoundException(ResourceNotFoundException ex, WebRequest req) {

        List<String> details = new LinkedList<>();
        details.add(ex.getLocalizedMessage());
        ErrorMessage error = new ErrorMessage(BAD_REQUEST, details);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    
}
