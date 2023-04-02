package iss.ibf2022.pafday08workshop.repository;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * db.game.aggregate([
     *  {
     *      $match: {'gid': id}
     *  },
     *  {
     *      $lookup: {
     *          from: 'comment',
     *          foreignField: 'gid',
     *          localField: 'gid',
     *          as: 'reviews'
     *      }
     *  },
     *  {
     *      $project: {
     *          game_id: '$gid',
     *          name: '$name',
     *          year: '$year',
     *          rank: '$rank',
     *          users_rated: 'users_rated',
     *          url: '$url',
     *          thumbnail: '$image',
     *          reviews: '$reviews'
     *      }
     *  }
     * ])
     */
    public List<Document> getGameById(String gameId) {

        System.out.println(">>> provided game id: " + gameId);

        // create criteria
        Criteria criteria = Criteria.where("gid").is(Integer.valueOf(gameId));

        // create operations
        MatchOperation getGame = Aggregation.match(criteria);
        LookupOperation getComments = Aggregation.lookup("comment", "gid", "gid", "reviews");
        ProjectionOperation getFields = Aggregation.project("name", "year", "users_rated", "url", "reviews")
            .and("gid").as("game_id")
            .and("ranking").as("rank")
            .and("image").as("thumbnail");

        // create pipeline
        Aggregation pipeline = Aggregation.newAggregation(getGame, getComments, getFields);

        // query result
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "game", Document.class);

        List<Document> docs = results.getMappedResults();

        return docs;
        
    }

    /*
     * db.game.aggregate([
     *      {
     *          $lookup: {
     *              from: 'comment',
     *              foreignField: 'gid',
     *              localField: 'gid',
     *              as: 'reviews'
     *          }
     *      },
     *      {
     *          $unwind: '$reviews'
     *      },
     *      {
     *          $match: {name: gameName}
     *      },
     *      {
     *          $sort: {
     *              'reviews.rating': -1 // for descending
     *          }    
     *      },
     *      {
     *          $limit: 1
     *      },
     *      {
     *          $project: {
     *              _id: '$gid',
     *              name: '$name',
     *              rating: '$reviews.rating',
     *              user: '$reviews.user',
     *              comment: '$reviews.c_text',
     *              review_id: '$reviews.c_id'
     *          }
     *      }
     *  ])
     */

    public List<Document> retrieveRatedGameList(Boolean ascendingRated) {

        List<Document> ratedGameList = new LinkedList<Document>();

        List<String> gameList = getUniqueGameName();
        // split the gameList to smaller size for testing
        List<String> gameTestList = gameList.subList(0, 3);

        for (String gameName : gameTestList) {

            // create criteria
            Criteria criteria = Criteria.where("name").is(gameName);

            // create stages
            LookupOperation combinedComment = Aggregation.lookup("comment", "gid", "gid", "reviews");
            UnwindOperation splitReviews = Aggregation.unwind("reviews");
            MatchOperation findGameByName = Aggregation.match(criteria);
            SortOperation sortReviewRating = ascendingRated ? Aggregation.sort(Sort.by(Direction.ASC, "reviews.rating")) : Aggregation.sort(Sort.by(Direction.DESC, "reviews.rating"));
            LimitOperation getFirstInRank = Aggregation.limit(1);
            ProjectionOperation filterFields = Aggregation.project("name")
                .and("gid").as("_id")
                .and("reviews.rating").as("rating")
                .and("reviews.user").as("user")
                .and("reviews.c_text").as("comment")
                .and("reviews.c_id").as("review_id");

            // create pipeline
            Aggregation pipeline = Aggregation.newAggregation(combinedComment, splitReviews, findGameByName, sortReviewRating, getFirstInRank, filterFields);

            // query result
            AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, "game", Document.class);
            
            // use getMappedResults or else loop through and add to list
            for (Document doc : results) {

                ratedGameList.add(doc);
            }

        }

        return ratedGameList;

    }

    /*
     * db.game.distinct('name')
     */
    public List<String> getUniqueGameName() {

        List<String> gameList = mongoTemplate.findDistinct(new Query(), "name", "game", String.class);

        return gameList;
    }
    
}