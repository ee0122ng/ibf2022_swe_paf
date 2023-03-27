package iss.ibf2022.pafday06workshop.model;

import java.time.LocalDateTime;

public class Game {

    private Integer gameId;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer userRated;
    private String url;
    private String image;
    private LocalDateTime timestamp;

    public Game() {
    }

    public Game(Integer gameId, String name) {
        this.gameId = gameId;
        this.name = name;
    }

    public Game(Integer gameId, String name, Integer year, Integer ranking, Integer userRated, String url, String image, LocalDateTime time) {
        this.gameId = gameId;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.userRated = userRated;
        this.url = url;
        this.image = image;
        this.timestamp = time;
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

    public Integer getRanking() {
        return this.ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUserRated() {
        return this.userRated;
    }

    public void setUserRated(Integer userRated) {
        this.userRated = userRated;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
}
