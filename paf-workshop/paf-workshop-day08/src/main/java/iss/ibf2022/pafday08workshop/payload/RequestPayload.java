package iss.ibf2022.pafday08workshop.payload;

import java.util.Date;
import java.util.List;

public class RequestPayload {

    private String rating;
    private List<RatedGame> games;
    private Date timestamp;

    public RequestPayload() {
    }

    public RequestPayload(String rating, List<RatedGame> games, Date timestamp) {
        this.rating = rating;
        this.games = games;
        this.timestamp = timestamp;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<RatedGame> getGames() {
        return this.games;
    }

    public void setGames(List<RatedGame> games) {
        this.games = games;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    
}
