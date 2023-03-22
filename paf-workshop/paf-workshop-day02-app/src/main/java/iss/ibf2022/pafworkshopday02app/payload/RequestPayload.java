package iss.ibf2022.pafworkshopday02app.payload;

import java.io.StringReader;
import java.util.List;

import iss.ibf2022.pafworkshopday02app.model.RSVP;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class RequestPayload {

    private String requestBody;
    private Integer requestStatus;

    public RequestPayload() {
    }

    public RequestPayload(String requestBody, Integer requestStatus) {
        this.requestBody = requestBody;
        this.requestStatus = requestStatus;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Integer getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }
    
}
