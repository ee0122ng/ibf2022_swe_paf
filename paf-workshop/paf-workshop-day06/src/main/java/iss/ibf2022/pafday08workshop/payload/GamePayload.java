package iss.ibf2022.pafday08workshop.payload;

import java.util.Date;
import java.util.List;

import iss.ibf2022.pafday08workshop.model.Game;

public class GamePayload {

    private List<Game> games;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Date timestamp;

    public GamePayload() {
    }

    public GamePayload(List<Game> games, Integer offset, Integer limit, Integer total, Date timestamp) {
        this.games = games;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.timestamp = timestamp;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
