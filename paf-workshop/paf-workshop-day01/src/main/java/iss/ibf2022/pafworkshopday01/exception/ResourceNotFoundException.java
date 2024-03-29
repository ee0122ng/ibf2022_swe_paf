package iss.ibf2022.pafworkshopday01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {

        super();
    }

    public ResourceNotFoundException(String message) {

        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {

        super(message, cause);
    }
    
}
