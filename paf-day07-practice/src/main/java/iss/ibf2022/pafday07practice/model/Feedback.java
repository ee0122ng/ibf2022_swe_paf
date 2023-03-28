package iss.ibf2022.pafday07practice.model;

import java.util.List;

public class Feedback {
    
    private Integer showId;
    private String userName;
    private Double rating;
    private List<String> comments;

    public Feedback() {
    }

    public Feedback(Integer showId, String userName, Double rating, List<String> comments) {
        this.showId = showId;
        this.userName = userName;
        this.rating = rating;
        this.comments = comments;
    }

    public Integer getShowId() {
        return this.showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getComments() {
        return this.comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

}
