package iss.ibf2022.pafday08workshop.model;

public class Review {
    
    private String cId;
    private String cText;

    public Review() {
    }

    public Review(String cId, String cText) {
        this.cId = cId;
        this.cText = cText;
    }

    public String getCId() {
        return this.cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCText() {
        return this.cText;
    }

    public void setCText(String cText) {
        this.cText = cText;
    }

}
