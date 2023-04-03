package iss.ibf2022.pafday08workshop.service;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.mongodb.client.result.UpdateResult;

import iss.ibf2022.pafday08workshop.exception.CommentResourceNotFoundException;
import iss.ibf2022.pafday08workshop.exception.ReviewResourceNotFoundException;
import iss.ibf2022.pafday08workshop.exception.UpdateResourceNotFoundException;
import iss.ibf2022.pafday08workshop.model.Edited;
import iss.ibf2022.pafday08workshop.model.Review;
import iss.ibf2022.pafday08workshop.payload.HistoricalCommentPayload;
import iss.ibf2022.pafday08workshop.payload.LatestCommentPayload;
import iss.ibf2022.pafday08workshop.payload.UpdatePayload;
import iss.ibf2022.pafday08workshop.repository.ReviewRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepo;

    // for part1: insert new review to reviews collection
    public Review insertReview(MultiValueMap<String, String> form) {

        try {

            // get game by id
            Document gameDoc = checkValidGameId(form.getFirst("gameId"));

            // convert form details to document for inserting
            Document formDoc = convertToDocument(form);
            formDoc.put("name", gameDoc.getString("name"));

            // get LocalDateTime
            LocalDateTime posted = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            formDoc.put("posted", posted);
            Document insertedDoc = reviewRepo.insertReview(formDoc);

            Review review = new Review();
            // store ObjectId as String in java object
            review.setId(String.valueOf(insertedDoc.getObjectId("_id")));
            review.setGameId(insertedDoc.getInteger("game_id"));
            review.setComment(insertedDoc.getString("comment"));
            review.setName(insertedDoc.getString("name"));

            // convert String to Date()
            review.setPosted(posted);
            review.setRating(insertedDoc.getInteger("rating"));
            review.setUser(insertedDoc.getString("user"));

            return review;

        } catch (Exception ex) {

            throw new ReviewResourceNotFoundException(ex.getLocalizedMessage());
        }

    }

    // for part2: update review by upserting document to comments collection 
    public UpdatePayload updateReview(String id, String updateInfo) {

        JsonObject toUpdate = convertToJson(updateInfo);

        // apply condition check for rating and comment
        try {
            Integer rating = toUpdate.getInt("rating");
            String comment = toUpdate.getString("comment", null);
            // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LocalDateTime posted = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            UpdateResult result = reviewRepo.updateReview(id, rating, comment, posted);
            
            UpdatePayload payload = new UpdatePayload();
            payload.setComment(comment);
            payload.setRating(rating);
            payload.setPosted(new Date());

            return payload;

        } catch (Exception ex) {

            throw new UpdateResourceNotFoundException(ex.getLocalizedMessage());

        }
        
    }


    // for part3: retrieve latest comment only per review id
    public LatestCommentPayload retrieveLatestComment(String reviewId) {

        try {

            Optional<List<Document>> opt = reviewRepo.getLatestComment(reviewId);

            List<Document> latestComment = opt.get();
            Document doc = latestComment.get(0);
    
            LatestCommentPayload payload = new LatestCommentPayload();
    
            payload.setComment(doc.getString("comment"));
            payload.setEdited(doc.getBoolean("edited"));
            payload.setID(doc.getInteger("ID"));
            payload.setName(doc.getString("name"));
            Date posted = doc.getDate("posted");
            payload.setPosted(posted.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            payload.setRating(doc.getInteger("rating"));
            payload.setUser(doc.getString("user"));
            payload.setTimestamp(LocalDateTime.now());
    
            return payload;

        } catch (Exception ex) {

            throw new CommentResourceNotFoundException(ex.getLocalizedMessage());
        }


    }

    // for part4: retrieve all history of comments per review id
    public HistoricalCommentPayload retrieveHistoricalComment(String reviewId) {

        try {
            Optional<List<Document>> opt = reviewRepo.getAllHistoricalComment(reviewId);

            List<Document> commentDocs = opt.get();
            LatestCommentPayload latestComment = retrieveLatestComment(reviewId);
    
            // create historical comment payload
            HistoricalCommentPayload payload = new HistoricalCommentPayload();
            payload.setComment(latestComment.getComment());
            payload.setID(latestComment.getID());
            payload.setName(latestComment.getName());
            payload.setPosted(latestComment.getPosted());
            payload.setRating(latestComment.getRating());
            payload.setTimestamp(LocalDateTime.now());
            payload.setUser(latestComment.getUser());
    
            List<Edited> editedList = commentDocs.stream().map(d -> new Edited(
                d.getString("comment"),
                d.getInteger("rating"),
                d.getDate("posted").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
            )).toList();
    
            payload.setEdited(editedList);
    
            return payload;

        } catch (Exception ex) {

            throw new CommentResourceNotFoundException(ex.getLocalizedMessage());
        }


    }

    // for part2: to handle input in JsonString
    public JsonObject convertToJson(String info) {

        JsonReader jReader = Json.createReader(new StringReader(info));
        JsonObject jObj = jReader.readObject();

        return jObj;
    }

    //for part1: convert to document before inserting to mongodb
    public Document convertToDocument(MultiValueMap<String, String> form) {

        Document doc = new Document();
        doc.put("game_id", Integer.valueOf(form.getFirst("gameId")));
        doc.put("user", form.getFirst("user"));
        doc.put("comment", form.getFirst("comment"));
        doc.put("name", form.getFirst("name"));
        doc.put("rating", Integer.valueOf(form.getFirst("rating")));

        return doc;
    }

    // for part1: check if game id is valid
    public Document checkValidGameId(String id) {

        List<Document> doc = reviewRepo.getGameById(id);

        if (doc.size() > 0) {

            Document gameDoc = new Document();
            gameDoc.put("game_id", Integer.valueOf(id));
            gameDoc.put("name", doc.get(0).getString("name"));

            return gameDoc;

        } else {

            throw new ReviewResourceNotFoundException("Game id:'%s' not found in the system".formatted(id));
        }
    }


    
}
