package iss.ibf2022.pafday06workshop.model;

public class GameResponse {

    private Integer game_id;
    private String name;

    public GameResponse() {
    }

    public GameResponse(Integer game_id, String name) {
        this.game_id = game_id;
        this.name = name;
    }

    public Integer getGame_id() {
        return this.game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
