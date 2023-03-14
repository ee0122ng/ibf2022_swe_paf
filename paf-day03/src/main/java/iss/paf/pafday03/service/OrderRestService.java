package iss.paf.pafday03.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import iss.paf.pafday03.model.Order;

@Service
public class OrderRestService {

    private static final String GET_ORDERDETAILSBYID_ENDPOINT = "http://localhost:8080/api/orders/{id}";

    RestTemplate restTemplate = new RestTemplate();

    public List<Order> retrieveOrderDetailsById(Integer id) throws RestClientException {

        RequestEntity<Void> req = RequestEntity.get(GET_ORDERDETAILSBYID_ENDPOINT).build();

        ResponseEntity<List<Order>> rep = restTemplate.exchange(GET_ORDERDETAILSBYID_ENDPOINT, HttpMethod.GET, req, new ParameterizedTypeReference<List<Order>>(){}, Map.of("id", id));

        return rep.getBody();
    }
    
}