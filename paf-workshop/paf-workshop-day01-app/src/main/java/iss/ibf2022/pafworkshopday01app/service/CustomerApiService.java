package iss.ibf2022.pafworkshopday01app.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import iss.ibf2022.pafworkshopday01app.model.Customer;
import iss.ibf2022.pafworkshopday01app.model.Order;

@Service
public class CustomerApiService {

    private RestTemplate restTemplate = new RestTemplate();

    private final String GET_ALL_ENDPOINT = "http://localhost:8080/api/customers";
    private final String GET_CUSTOMER_BY_ID_ENDPOINT = "http://localhost:8080/api/customers/{cust_id}";
    private final String GET_ORDER_BY_CUSTOMERID_ENDPOINT = "http://localhost:8080/api/customers/{cust_id}/orders";

    public List<Customer> getCustomerList(String limit, String offset) {

        final String URL = UriComponentsBuilder
            .fromUriString(GET_ALL_ENDPOINT)
            .queryParam("limit", limit)
            .queryParam("offset", offset)
            .toUriString();

        RequestEntity<Void> req = RequestEntity.get(URL).build();

        // ResponseEntity<List<Customer>> rep = restTemplate.exchange(req, new ParameterizedTypeReference<List<Customer>>(){});
        ResponseEntity<List<Customer>> rep = restTemplate.exchange(URL, HttpMethod.GET, req, new ParameterizedTypeReference<List<Customer>>(){});
        
        return rep.getBody();

    }

    public Customer getCustomerById(Integer id) {
        
        return restTemplate.getForObject(GET_CUSTOMER_BY_ID_ENDPOINT, Customer.class, Map.of("cust_id", id));
    }

    public List<Order> getOrderByCustomerId(Integer id) {
        
        ResponseEntity<List<Order>> rep = restTemplate.exchange(GET_ORDER_BY_CUSTOMERID_ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>(){}, id);

        return rep.getBody();
    }

    
}
