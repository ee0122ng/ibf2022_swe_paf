package iss.ibf2022.pafday08workshop.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import iss.ibf2022.pafday08workshop.exception.CommentResourceNotFoundException;
import iss.ibf2022.pafday08workshop.exception.ReviewResourceNotFoundException;
import iss.ibf2022.pafday08workshop.model.Edited;

@Repository
public class ReviewRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    
    /*
     * db.reviews.insert({
     *      'user': user,
     *      'rating': rating,
     *      'comment': comment,
     *      'id': gameId,
     *      'posted': date,
     *      'name': gameName
     * })
     */
    public Document insertReview(Document doc) {

        Document result = mongoTemplate.insert(doc, "reviews");

        return result;
    }

    /*
     * df.game.find({'gid': id})
     */
    public List<Document> getGameById(String id) {

        Criteria criteria = Criteria.where("gid").is(Integer.valueOf(id));
        Query query = Query.query(criteria);

        List<Document> result = mongoTemplate.find(query, Document.class, "game");

        return result;
    }

    /*
     * db.reviews.find({_id: ObjectId(id)})
     */
    public Document getReviewById(String id) {

        ObjectId objId = new ObjectId(id);

        Document result = mongoTemplate.findById(objId, Document.class, "reviews");

        return Optional.of(result).orElseThrow(ReviewResourceNotFoundException::new);
    }
    
    /*
     * db.comments.update(
     *  {game_id: 1, user: user, name: name},
     *  {
     *      $set: {'rating': rating},
     *      $set: {'comment': some_comments},
     *      $set: {'posted': posted_date},
     *      $push: {
     *          'edited': {'comment': comment, 'rating': rating, 'posted': posted}
     *      }
     *  },
     *  {
     *      upsert: true
     *  }
     * )
     */
    public UpdateResult updateReview(String id, Integer rating, String comment, LocalDateTime posted) {

        try {
            Document review = getReviewById(id);

            Criteria criteria = Criteria.where("ID").is(review.getInteger("game_id"))
                .and("name").is(review.getString("name"))
                .and("user").is(review.getString("user"));

            Query query = Query.query(criteria);

            // create an Object with property same as the embeded document
            Edited editedField = new Edited();

            if (null == comment || comment.isEmpty()) {
                editedField.setRating(rating);
                editedField.setPosted(posted);

                Update updateOps = new Update().set("rating", review.getInteger("rating"))
                    .set("comment", review.getString("comment"))
                    .set("posted", review.get("posted"))
                    .push("edited").each(List.of(editedField).toArray());
                UpdateResult result = mongoTemplate.upsert(query, updateOps, "comments");
                return result;
            }

            editedField.setRating(rating);
            editedField.setComment(comment);
            editedField.setPosted(posted);
            Update updateOps = new Update().set("rating", review.getInteger("rating"))
                .set("comment", review.getString("comment"))
                .set("posted", review.get("posted"))
                .push("edited").each(List.of(editedField).toArray());
            UpdateResult result = mongoTemplate.upsert(query, updateOps, "comments");
            return result;

        } catch (Exception ex) {

            throw new ReviewResourceNotFoundException("Review id:'%s' provided not found".formatted(id));
        }
        
    }

    /*
     * db.comments.aggregate([
     *  {
     *      $match: {_id: ObjectId(id)}
     *  },
     *  {
     *      $unwind: '$edited'
     *  },
     *  {
     *      $sort: {'edited.posted': -1}
     *  },
     *  {
     *      $limit: 1
     *  },
     *  {
     *      $project: {
     *          user: '$user',
     *          rating: '$edited.rating',
     *          comment: '$edited.comment',
     *          ID : '$ID',
     *          posted: '$edited.posted',
     *          name: '$name',
     *          edited: {
     *              $cond: {
     *                  if: {gte: [{'count': {$sum: 1}}, 1]}
     *                  then: true,
     *                  else: false
     *              }
     *          }
     *      }
     *  }
     * ])
     */
    public Optional<List<Document>> getLatestComment(String reviewId) {

        try {

            Document reviewDoc = getReviewById(reviewId);

            // create criteria
            Criteria criteria = Criteria.where("ID").is(reviewDoc.getInteger("game_id"));

            // create stages
            MatchOperation matchId = Aggregation.match(criteria);
            UnwindOperation unwindEditedField = Aggregation.unwind("edited");
            SortOperation sortEditedPostedField = Aggregation.sort(Sort.by(Direction.DESC, "edited.posted"));
            LimitOperation limitReturn = Aggregation.limit(1);
            ProjectionOperation customReturnedFields = Aggregation.project("user", "edited.rating", "edited.comment", "ID", "edited.posted", "name")
                .and(AggregationExpression.from(
                    MongoExpression.create("""
                            $cond: {
                                if: {gte: [{'count': {$sum:1}}, 1]}
                                then: true,
                                else: false
                            }
                            """)
                )).as("edited");

            // create pipeline
            Aggregation pipeline = Aggregation.newAggregation(matchId, unwindEditedField, sortEditedPostedField, limitReturn, customReturnedFields);

            // query result
            // dont use getRawResult() for return as the read is in different format
            AggregationResults<Document> result = mongoTemplate.aggregate(pipeline, "comments", Document.class);
            
            return Optional.of(result.getMappedResults());

        } catch (Exception ex) {

            throw new CommentResourceNotFoundException("Review id:'%s' provided not found in the system".formatted(reviewId));
        }

    }

    /*
     * db.comments.find({_id: ObjectId(id)})
     */
    public Optional<List<Document>> getAllHistoricalComment(String reviewId) {

        try {

            Document reviewDoc = getReviewById(reviewId);

            // create criteria
            Criteria criteria = Criteria.where("ID").is(reviewDoc.getInteger("game_id"));
            Query query = Query.query(criteria);

            List<Document> result = mongoTemplate.find(query, Document.class, "comments");

            return Optional.of(result);

        } catch (Exception ex) {

            throw new CommentResourceNotFoundException("Review id:'%s' provided not found in the system".formatted(reviewId));

        }

    }

}
