package iss.ibf2022.pafworkshopday03app.payload;

public class RequestPayload {

    private Integer status;
    private String body;

    public RequestPayload() {
    }

    public RequestPayload(Integer status, String body) {
        this.status = status;
        this.body = body;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
}
