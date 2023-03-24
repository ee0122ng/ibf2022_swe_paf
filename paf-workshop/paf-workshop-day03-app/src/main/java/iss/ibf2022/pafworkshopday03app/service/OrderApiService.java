package iss.ibf2022.pafworkshopday03app.service;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import iss.ibf2022.pafworkshopday03app.model.Order;
import iss.ibf2022.pafworkshopday03app.payload.RequestPayload;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class OrderApiService {
    
    private RestTemplate restTemplate = new RestTemplate();

    private final String GET_ORDER_BY_ID_ENDPOINT = "http://localhost:8080/api/order/total/{orderId}";

    public RequestPayload getOrderById(String id) {

        try {
            ResponseEntity<String> rep = restTemplate.exchange(GET_ORDER_BY_ID_ENDPOINT, HttpMethod.GET, null, String.class, Map.of("orderId", id));
            return new RequestPayload(rep.getStatusCode().value(), rep.getBody());

        } catch (Exception ex) {

            System.out.println(ex.getLocalizedMessage());
            return new RequestPayload(404, ex.getMessage());
        }
        
    }

    public List<Order> getObjectFromPayload(RequestPayload payload) {

        String json = payload.getBody();

        JsonReader jReader = Json.createReader(new StringReader(json));
        JsonArray jArray = jReader.readArray();

        List<JsonObject> jObjList = jArray.stream().map(jValue -> jValue.asJsonObject()).toList();
        List<Order> orderList = jObjList.stream().map(obj -> new Order(
            obj.getInt("orderId"),
            java.sql.Date.valueOf(obj.getString("orderDate")),
            obj.getInt("customerId"),
            (float) obj.getJsonNumber("totalCost").intValue(),
            (float) obj.getJsonNumber("costPrice").intValue()
        )).toList();

        return orderList;
    }

    public JsonObject getErrorMessageFromPayload(RequestPayload payload) {

        JsonObject jObj = Json.createObjectBuilder()
            .add("status", payload.getStatus())
            .add("details", payload.getBody())
            .build();

        return jObj;
    }

}