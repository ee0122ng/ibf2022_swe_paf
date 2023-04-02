package iss.ibf2022.pafday08workshop.service;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafday08workshop.model.Game;
import iss.ibf2022.pafday08workshop.model.Review;
import iss.ibf2022.pafday08workshop.payload.RatedGame;
import iss.ibf2022.pafday08workshop.payload.RequestPayload;
import iss.ibf2022.pafday08workshop.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepo;

    public List<Document> retrieveGameById(String gameId) {

        return gameRepo.getGameById(gameId);

    }

    public RequestPayload retrieveRatedGameList(Boolean ascendingRate) {

        List<Document> ratedGameList = gameRepo.retrieveRatedGameList(ascendingRate);

        List<RatedGame> ratedGames = ratedGameList.stream().map(d -> new RatedGame(
            d.getInteger("_id"),
            d.getString("name"),
            d.getInteger("rating"), 
            d.getString("user"),
            d.getString("comment"),
            d.getString("review_id"))).toList();

        RequestPayload payload = convertToRequestPayload(ratedGames, ascendingRate);

        return payload;
    }

    public RequestPayload convertToRequestPayload(List<RatedGame> ratedGames, Boolean ascendingRated) {

        RequestPayload payload = new RequestPayload();

        // describe the order type: highest or lowest
        if (ascendingRated) {
            payload.setRating("lowest");
        } else {
            payload.setRating("highest");
        }

        payload.setGames(ratedGames);
        payload.setTimestamp(new Date());

        return payload;
        
    }

    // public JsonObject convertToJson(List<RatedGame> ratedGames, Boolean ascendingRated) {
        
    //     JsonArrayBuilder jArrBuilder = Json.createArrayBuilder();

    //     for (RatedGame g : ratedGames) {

    //         JsonObject jObj = Json.createObjectBuilder()
    //             .add("_id", g.get_id())
    //             .add("name", g.getName())
    //             .add("rating", g.getRating())
    //             .add("user", g.getUser())
    //             .add("comment", g.getComment())
    //             .add("review_id", g.getReview_id())
    //             .build();
            
    //             jArrBuilder.add(jObj);
    //     }

    //     JsonArray jArr = jArrBuilder.build();

    //     // create a Json Object in the proper payload format
    //     JsonObject jPayload = Json.createObjectBuilder()
    //         .add("rating", ascendingRated ? "lowest" : "highest")
    //         .add("games", jArr)
    //         .add("timestamp", new Date().toString())
    //         .build();

    //     return jPayload; 
    // }

    public List<Game> convertToGame(List<Document> docs) {

        // create game objects
        List<Game> games = docs.stream().map(d -> new Game(
            d.getInteger("game_id"),
            d.getString("name"),
            d.getInteger("year"),
            d.getInteger("rank"),
            d.getInteger("userRated"),
            d.getString("url"),
            d.getString("thumbnail")
        )).toList();

        // get reviews
        List<List<Document>> reviews = docs.stream().map(d -> d.getList("reviews", Document.class)).toList();
        List<List<Review>> revList = reviews.stream().map(docList -> docList.stream().map(d -> new Review(
            d.getString("c_id"),
            d.getString("c_text")
        )).toList()).toList();

        // map review to game
        for (int i = 0; i < games.size(); i++) {
            games.get(i).setReviews(revList.get(i));
        }

        return games;

    }

}