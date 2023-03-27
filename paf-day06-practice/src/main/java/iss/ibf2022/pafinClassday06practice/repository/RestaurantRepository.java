package iss.ibf2022.pafinClassday06practice.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import iss.ibf2022.pafinClassday06practice.exception.NullValueException;

@Repository
public class RestaurantRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * db.restaurant.distinct('type_of_food', {'type_of_food': {$nin:['*NEW*']}})
     */
    // get distinct type of food
    public List<String> retrieveUniqueTypeOfFood() {

        // create criteria
        Criteria criteria = Criteria.where("type_of_food").nin("*NEW*");

        // create query
        Query query = Query.query(criteria);

        // use mongotemplate to retrive result
        List<String> result = mongoTemplate.findDistinct(query, "type_of_food", "restaurant", String.class);

        return result;

    }

    /*
     * db.restaurant.find({'type_of_food':{$in:['Chinese']}}).sort({'name':1})
     */
    public List<Document> retrieveRestaurantByType(String type) {

        List<String> foodType = retrieveUniqueTypeOfFood();
        // check if the input is valid
        Boolean validType = foodType.stream().anyMatch(t -> t.equalsIgnoreCase(type));
        List<Document> result = new LinkedList<>();

        if (validType) {

            // create criteria
            Criteria criteria = Criteria.where("type_of_food").in(type);

            // create query
            Query query = Query.query(criteria);

            // use mongotemplate to retrieve result
            result = mongoTemplate.find(query, Document.class, "restaurant");

        }

        return Optional.of(result).orElseThrow(NullValueException::new);
        
    }
    
}
