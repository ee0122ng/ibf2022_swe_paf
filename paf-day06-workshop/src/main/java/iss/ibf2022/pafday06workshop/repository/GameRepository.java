package iss.ibf2022.pafday06workshop.repository;

import java.io.StringReader;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafday06workshop.exception.ResourceNotFoundException;
import iss.ibf2022.pafday06workshop.model.Game;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Repository
public class GameRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * db.game.find({}).limit(25).skip(0)
     */
    public List<String> getAllGame(Integer limit, Integer skip) {

        Query query = Query.query(new Criteria()).skip(skip).limit(limit);
        query.fields().exclude("_id").include("gid", "name");

        List<Document> docs = mongoTemplate.find(query, Document.class, "game");
        List<String> json = docs.stream().map(doc -> doc.toJson()).toList();

        if (docs.size() > 0) {

            return json;

        } else {

            throw new ResourceNotFoundException("No resource found for the provided range");
        }

    }

    /*
     * db.game.find({'ranking':value})
     */
    public List<String> getGameByRank (Integer limit, Integer skip) {

        Query query = Query.query(new Criteria()).with(Sort.by(Sort.Direction.ASC, "ranking")).skip(skip).limit(limit);
        query.fields().exclude("_id").include("gid", "name");

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        if (result.size() > 0) {
            return result.stream().map(r -> r.toJson()).toList();

        } else {

            throw new ResourceNotFoundException("No resource found");
        }
        
    }

    /*
     * db.game.find({'gid':1})
     */
    public Game getGameById(Integer id) {

        Criteria criteria = Criteria.where("gid").is(id);
        Query query = Query.query(criteria);

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        if(result.size() > 0) {

            String json = result.get(0).toJson();
            JsonReader jReader = Json.createReader(new StringReader(json));
            JsonObject jObj = jReader.readObject();

            Game game = new Game();
            game.setGameId(jObj.getInt("gid"));
            game.setName(jObj.getString("name"));
            game.setYear(jObj.getInt("year"));
            game.setRanking(jObj.getInt("ranking"));
            game.setUserRated(jObj.getInt("users_rated"));
            game.setUrl(jObj.getString("url"));
            game.setImage(jObj.getString("image"));

            return game;

        } else {

            throw new ResourceNotFoundException("Id=%d provided not found".formatted(id));
        }
    }
    
}
