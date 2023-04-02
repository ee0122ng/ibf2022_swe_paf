package iss.ibf2022.pafday08workshop.payload;

public class RatedGame {
    
    private Integer _id;
    private String name;
    private Integer rating;
    private String user;
    private String comment;
    private String review_id;

    public RatedGame() {
    }

    public RatedGame(Integer _id, String name, Integer rating, String user, String comment, String review_id) {
        this._id = _id;
        this.name = name;
        this.rating = rating;
        this.user = user;
        this.comment = comment;
        this.review_id = review_id;
    }

    public Integer get_id() {
        return this._id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReview_id() {
        return this.review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    @Override
    public String toString() {
        return "{" +
            " _id='" + get_id() + "'" +
            ", name='" + getName() + "'" +
            ", rating='" + getRating() + "'" +
            ", user='" + getUser() + "'" +
            ", comment='" + getComment() + "'" +
            ", review_id='" + getReview_id() + "'" +
            "}";
    }


}
