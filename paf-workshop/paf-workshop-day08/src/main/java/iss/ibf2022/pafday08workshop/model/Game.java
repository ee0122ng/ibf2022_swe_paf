package iss.ibf2022.pafday08workshop.model;

import java.util.Date;
import java.util.List;

public class Game {

    private Integer gameId;
    private String name;
    private Integer year;
    private Integer rank;
    private Integer usersRated;
    private String url;
    private String thumbnail;
    private List<Review> reviews;
    private Date timestamp = new Date();
    
    public Game() {
    }

    public Game(Integer gameId, String name, Integer year, Integer rank, Integer usersRated, String url, String thumbnail) {
        this.gameId = gameId;
        this.name = name;
        this.year = year;
        this.rank = rank;
        this.usersRated = usersRated;
        this.url = url;
        this.thumbnail = thumbnail;
    }

    public Game(Integer gameId, String name, Integer year, Integer rank, Integer usersRated, String url, String thumbnail, List<Review> reviews, Date timestamp) {
        this.gameId = gameId;
        this.name = name;
        this.year = year;
        this.rank = rank;
        this.usersRated = usersRated;
        this.url = url;
        this.thumbnail = thumbnail;
        this.reviews = reviews;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getGameId() {
        return this.gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getUsersRated() {
        return this.usersRated;
    }

    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    @Override
    public String toString() {
        return "{" +
            " gameId='" + getGameId() + "'" +
            ", name='" + getName() + "'" +
            ", year='" + getYear() + "'" +
            ", rank='" + getRank() + "'" +
            ", usersRated='" + getUsersRated() + "'" +
            ", url='" + getUrl() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", reviews='" + getReviews() + "'" +
            "}";
    }


}
