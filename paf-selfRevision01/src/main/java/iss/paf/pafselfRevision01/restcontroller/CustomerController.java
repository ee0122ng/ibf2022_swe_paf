package iss.paf.pafselfRevision01.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import iss.paf.pafselfRevision01.model.Customer;
import iss.paf.pafselfRevision01.model.Order;
import iss.paf.pafselfRevision01.service.CustomerService;
import jakarta.json.Json;
import jakarta.json.JsonArray;

@RestController
@RequestMapping(path={"/api/customers"})
public class CustomerController {
    
    @Autowired
    CustomerService customerSvc;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomersWithRange(@RequestParam MultiValueMap<String, String> record) {

        // set a default value for limit and offset
        Integer limit = record.getFirst("limit") == null ? 5 : Integer.valueOf(record.getFirst("limit"));
        Integer offset = record.getFirst("offset") == null ? 0 : Integer.valueOf(record.getFirst("offset"));

        List<Customer> customerList = customerSvc.retrieveCustomersWithLimitOffset(limit, offset);

        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    @GetMapping(path={"/{customer_id}"})
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customer_id) {

        Optional<Customer> opt = customerSvc.retrieveCustomerById(customer_id);

        try {

            Customer customer = opt.get();
            
            return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(customer).toString());

        } catch (Exception ex) {

            String error = "No record found for customer_id = %d".formatted(customer_id);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        
    }

    @GetMapping(path={"/{customer_id}/orders"})
    public ResponseEntity<String> getOrderByCustomerId(@PathVariable Integer customer_id) {

        Optional<List<Order>> opt = customerSvc.retrieveOrderByCustomerId(customer_id);

        try {

            List<Order> orderList = opt.get();

            // create a JsonArray
            JsonArray jArray = Json.createArrayBuilder(orderList).build();

            return ResponseEntity.status(HttpStatus.OK).body(jArray.toString());

        } catch (Exception e) {

            String error = "No record found for customer_id = %d".formatted(customer_id);
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
