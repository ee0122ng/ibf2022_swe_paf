package iss.ibf2022.pafworkshopday03.payload;

import java.util.List;

public class ErrorMessage {
    
    private String message;
    private List<String> details;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return this.details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
