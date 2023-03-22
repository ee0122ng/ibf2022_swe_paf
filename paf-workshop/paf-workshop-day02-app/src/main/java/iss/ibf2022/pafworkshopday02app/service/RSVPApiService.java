package iss.ibf2022.pafworkshopday02app.service;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import iss.ibf2022.pafworkshopday02app.model.RSVP;
import iss.ibf2022.pafworkshopday02app.payload.RequestPayload;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class RSVPApiService {

    private RestTemplate restTemplate = new RestTemplate();

    // define various endpoints
    private final String GET_ALL_RECORDS_ENDPOINT = "http://localhost:8080/api/rsvps";
    private final String GET_RECORD_BY_NAME_ENDPOINT = "http://localhost:8080/api/rsvp"; //need to add query for name
    private final String INSERT_NEW_RECORD_ENDPOINT = "http://localhost:8080/api/rsvp";
    private final String UPDATE_RECORD_ENDPOINT = "http://localhost:8080/api/rsvp/{email}";
    private final String COUNT_RECORDS_ENDPOINT = "http://localhost:8080/api/rsvps/count";

    public RequestPayload retrieveAllRecords() {

        ResponseEntity<String> rep =  restTemplate.exchange(GET_ALL_RECORDS_ENDPOINT, HttpMethod.GET, null, String.class);
        Integer status = rep.getStatusCode().value();
        String body = rep.getBody();

        RequestPayload payload = new RequestPayload(body, status);

        return payload;

    }

    public RequestPayload retrieveRecordByName(String name) {

        final String URL = UriComponentsBuilder
            .fromUriString(GET_RECORD_BY_NAME_ENDPOINT)
            .queryParam("q", name)
            .toUriString();

        ResponseEntity<String> rep = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        Integer status = rep.getStatusCode().value();
        String body = rep.getBody();

        RequestPayload payload = new RequestPayload(body, status);

        return payload;
    }

    // need to prepare the RequestEntity body
    public RequestPayload createNewRecord(MultiValueMap<String, String> form) {

        RequestEntity<MultiValueMap<String, String>> req = RequestEntity
            .post(INSERT_NEW_RECORD_ENDPOINT)
            .body(form);

        ResponseEntity<String> rep = restTemplate.exchange(INSERT_NEW_RECORD_ENDPOINT, HttpMethod.POST, req, String.class);
        Integer status = rep.getStatusCode().value();
        String body = rep.getBody();

        RequestPayload payload = new RequestPayload(body, status);

        return payload;

    }

    // need to prepare the RequestEntity body
    public RequestPayload updateRecord(MultiValueMap<String, String> form) {
        
        String email = form.getFirst("email");

        RequestEntity<MultiValueMap<String, String>> req = RequestEntity
            .put(UPDATE_RECORD_ENDPOINT)
            .body(form);

        ResponseEntity<String> rep = restTemplate.exchange(UPDATE_RECORD_ENDPOINT, HttpMethod.PUT, req, String.class, Map.of("email", email));
        Integer status = rep.getStatusCode().value();
        String body = rep.getBody();

        RequestPayload payload = new RequestPayload(body, status);

        return payload;
            
    }

    public RequestPayload countRecords() {

        ResponseEntity<Integer> rep = restTemplate.exchange(COUNT_RECORDS_ENDPOINT, HttpMethod.GET, null, Integer.class);
        Integer status = rep.getStatusCode().value();
        String body = String.valueOf(rep.getBody());
        
        RequestPayload payload = new RequestPayload(body, status);

        return payload;

    }

    public List<RSVP> convertPayloadToCollection(String body) {
        JsonReader jReader = Json.createReader(new StringReader(body));
        JsonArray jArray = jReader.readArray();
        
        // convert json Array to list of json Object then Java POJO
        List<JsonObject> jObjList = jArray.stream().map(jValue -> jValue.asJsonObject()).toList();
        List<RSVP> rsvpList = jObjList.stream().map(jObj -> new RSVP(
            jObj.getInt("id"),
            jObj.getString("customerName"),
            jObj.getString("email"),
            jObj.getString("phone"),
            java.sql.Date.valueOf(jObj.getString("confirmationDate")),
            jObj.getString("comments")
        )).toList();

        return rsvpList;
    }

    public RSVP convertPayloadToObject(String body) {
        JsonReader jReader = Json.createReader(new StringReader(body));
        JsonObject jObj = jReader.readObject();

        // convert json Object to Java POJO
        RSVP rsvp = new RSVP(
            jObj.getInt("id"),
            jObj.getString("customerName"),
            jObj.getString("email"),
            jObj.getString("phone"),
            java.sql.Date.valueOf(jObj.getString("confirmationDate")),
            jObj.getString("comments")
        );

        return rsvp;
    }
    
}
