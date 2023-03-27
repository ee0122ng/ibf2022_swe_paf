package iss.ibf2022.pafday06workshop.payload;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.List;

import iss.ibf2022.pafday06workshop.model.GameResponse;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class GamePayload {

    private List<GameResponse> games;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private LocalDateTime timestamp;

    public List<GameResponse> getGames() {
        return this.games;
    }

    public void setGames(List<GameResponse> games) {
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

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public GamePayload createGamePayload(List<String> json, Integer offset, Integer limit) {

        // convert json to payload object
        List<JsonObject> jObj = json.stream().map(j -> Json.createReader(new StringReader(j)).readObject()).toList();
        List<GameResponse> gameRep = jObj.stream().map(o -> new GameResponse(
            o.getInt("gid"),
            o.getString("name")
        )).toList();

        GamePayload gamePayload = new GamePayload();
        gamePayload.setGames(gameRep);
        gamePayload.setLimit(limit);
        gamePayload.setOffset(offset);
        gamePayload.setTotal(json.size());
        gamePayload.setTimestamp(LocalDateTime.now());

        return gamePayload;

    }
    
}
