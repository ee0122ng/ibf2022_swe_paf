package iss.ibf2022.pafday08workshop.payload;

import java.util.Date;

public class UpdatePayload {

    private String comment;
    private Integer rating;
    private Date posted;

    public UpdatePayload() {
    }

    public UpdatePayload(String comment, Integer rating, Date posted) {
        this.comment = comment;
        this.rating = rating;
        this.posted = posted;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getPosted() {
        return this.posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    
}
