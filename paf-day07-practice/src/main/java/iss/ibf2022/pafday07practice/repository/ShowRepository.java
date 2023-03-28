package iss.ibf2022.pafday07practice.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import iss.ibf2022.pafday07practice.exception.ResourceNotFoundException;
import iss.ibf2022.pafday07practice.model.Feedback;

@Repository
public class ShowRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * db.tvshows.distinct('name')
     */
    public List<String> getListOfShowName() {

        Query query = Query.query(new Criteria());
        
        return mongoTemplate.findDistinct(query, "name", "tvshows", String.class);
    }

    /*
     * db.tvshows.find({'name': '2 Broke Girls'})
     */
    public Document getShowByName(String name) { 

        Criteria criteria = Criteria.where("name").is(name);

        Query query = Query.query(criteria);
        query.fields().exclude("_id").include("id", "name", "language", "genres", "rating", "runtime", "summary");

        List<Document> showDetails = mongoTemplate.find(query, Document.class, "tvshows");

        if (showDetails.size() > 0) {

            return showDetails.get(0);

        } else {

            throw new ResourceNotFoundException("Show name '%s' provided not found".formatted(name));
        }
    }

    /*
     * db.tvshows.update(
     *  {'id': 123},
     *  {$push: 
     *     {
     *       comments: 
     *          {
     *              'user': 'soonhang',
     *              'rating': '7.0',
     *              'comment': 'pretty interesting'
     *          }
     *     }
     *  },
     *  {upsert: true}
     * )
     */
    public Long upsertFeedback(Feedback feedback) {

        if (feedback.getUserName().isEmpty() || null == feedback.getRating()) {

            return -1000L;
        } 

        Document doc = new Document();
        doc.put("user_name", feedback.getUserName());
        doc.put("rating", feedback.getRating());
        doc.put("comment", feedback.getComments());

        Criteria criteria = Criteria.where("id").is(feedback.getShowId());

        Query query = Query.query(criteria);
        Update updateOps = new Update().push("comments", doc);

        UpdateResult updateResult = mongoTemplate.upsert(query, updateOps, "tvshows");

        return updateResult.getModifiedCount();

    }
    
}
