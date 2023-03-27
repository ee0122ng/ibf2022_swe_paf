package iss.ibf2022.pafinClassday06practice.Service;

import java.io.StringReader;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafinClassday06practice.model.Restaurant;
import iss.ibf2022.pafinClassday06practice.repository.RestaurantRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepo;

    public List<String> getUniqueTypeOfFood() {

        return restaurantRepo.retrieveUniqueTypeOfFood();
    }

    public List<Restaurant> getRestaurantByFoodType(String type) {

        List<Document> docs = restaurantRepo.retrieveRestaurantByType(type);
        List<Restaurant> restaurantList = convertBjsonToPojo(docs);

        return restaurantList;
    }

    public List<Restaurant> convertBjsonToPojo(List<Document> docs) {

        List<String> jsonList = docs.stream().map(o -> o.toJson()).toList();
        List<JsonObject> jsonObjectList = jsonList.stream().map(j -> Json.createReader(new StringReader(j)).readObject()).toList();
        List<Restaurant> restaurantList = jsonObjectList.stream().map(jObj -> new Restaurant(
            jObj.getString("name"),
            jObj.getString("URL"),
            jObj.getString("address"),
            jObj.getString("address line 2"),
            jObj.getString("outcode"),
            jObj.getString("postcode"),
            jObj.getJsonNumber("rating").doubleValue(),
            jObj.getString("type_of_food")
        )).toList();

        return restaurantList;
    }
    
}