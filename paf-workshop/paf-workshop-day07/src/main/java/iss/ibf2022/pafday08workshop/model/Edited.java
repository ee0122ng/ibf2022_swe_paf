package iss.ibf2022.pafday08workshop.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Edited {

    private String comment;
    private Integer rating;
    private LocalDateTime posted;

    public Edited() {
    }

    public Edited(String comment, Integer rating, LocalDateTime posted) {
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

    public LocalDateTime getPosted() {
        return this.posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }


}
