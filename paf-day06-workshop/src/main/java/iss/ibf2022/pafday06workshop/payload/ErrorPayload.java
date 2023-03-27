package iss.ibf2022.pafday06workshop.payload;

public class ErrorPayload {
    
    private String message;
    private String details;

    public ErrorPayload() {
    }

    public ErrorPayload(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
