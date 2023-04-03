package iss.ibf2022.pafday08workshop.payload;

import java.time.LocalDateTime;

public class LatestCommentPayload {
    
    private String user;
    private Integer rating;
    private String comment;
    private Integer ID;
    private LocalDateTime posted;
    private String name;
    private Boolean edited;
    private LocalDateTime timestamp;

    public LatestCommentPayload() {
    }

    public LatestCommentPayload(String user, Integer rating, String comment, Integer ID, LocalDateTime posted, String name, Boolean edited, LocalDateTime timestamp) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.ID = ID;
        this.posted = posted;
        this.name = name;
        this.edited = edited;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public LocalDateTime getPosted() {
        return this.posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isEdited() {
        return this.edited;
    }

    public Boolean getEdited() {
        return this.edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
