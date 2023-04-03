package iss.ibf2022.pafday08workshop.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Review {
    
    private String id;
    
    private String user;

    private Integer rating;

    private String comment;

    private Integer gameId;

    private LocalDateTime posted;

    private String name;


    public Review() {
    }

    public Review(String user, Integer rating, String comment, Integer gameId, LocalDateTime posted, String name) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.gameId = gameId;
        this.posted = posted;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getGameId() {
        return this.gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", rating='" + getRating() + "'" +
            ", comment='" + getComment() + "'" +
            ", gameId='" + getGameId() + "'" +
            ", posted='" + getPosted() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}
