package iss.ibf2022.pafday08workshop.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * db.game.find({}).limit(25).skip(0)
     */
    public List<Document> getGames(Integer limit, Integer offset) {

        Query query = Query.query(new Criteria()).limit(limit).skip(offset);

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        return result;
        
    }
    
    /*
     * db.game.find({}).sort({'ranking': 1}).limit(25).offset(0)
     */
    public List<Document> getGamesByRank(Integer limit, Integer offset) {

        Query query = Query.query(new Criteria()).with(Sort.by(Sort.Direction.ASC, "ranking")).limit(limit).skip(offset);

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        return result;
    }

    /*
     * db.game.find({'gid': 1})
     */
    public List<Document> getGameById(String id) {

        Criteria criteria = Criteria.where("gid").is(Integer.valueOf(id));
        Query query = Query.query(criteria);

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        return result;
    }

}
