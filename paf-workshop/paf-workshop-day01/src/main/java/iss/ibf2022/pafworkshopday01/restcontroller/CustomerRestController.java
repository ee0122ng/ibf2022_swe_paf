package iss.ibf2022.pafworkshopday01.restcontroller;

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

import iss.ibf2022.pafworkshopday01.exception.ResourceNotFoundException;
import iss.ibf2022.pafworkshopday01.model.Customer;
import iss.ibf2022.pafworkshopday01.model.Order;
import iss.ibf2022.pafworkshopday01.service.CustomerService;

@RestController
@RequestMapping(path={"/api/customers"})
public class CustomerRestController {

    @Autowired
    CustomerService custSvc;

    @GetMapping
    public ResponseEntity<Object> getCustomerList(@RequestParam MultiValueMap<String, String> form) {

        String limit = "";
        String offset = "";

        // set default value
        if (null == form.getFirst("limit") || form.getFirst("limit").isEmpty()) {
            limit = String.valueOf(5);
        } else {
            limit = form.getFirst("limit");
        }
        if (null == form.getFirst("offset") || form.getFirst("offset").isEmpty()) {
            offset = String.valueOf(0);
        } else {
            offset = form.getFirst("offset");
        }

        Optional<List<Customer>> opt = custSvc.retrieveAll(limit, offset);

        try {

            List<Customer> customerList = opt.get();
            return ResponseEntity.status(HttpStatus.OK).body(customerList);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Value provided is out of range");
            // throw new ResourceNotFoundException("Value provided is out of range");

        }

    }

    @GetMapping(path={"/{cust_id}"})
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer cust_id) {

        Optional<Customer> opt = custSvc.retrieveById(cust_id);

        try {

            Customer customer = opt.get();
            return ResponseEntity.status(HttpStatus.OK).body(customer);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No customer record found");
            // throw new ResourceNotFoundException("Customer details for selected Id=%d provided not found".formatted(cust_id));

        }
    }

    @GetMapping(path={"/{cust_id}/orders"})
    public ResponseEntity<Object> getOrderByCustomerId(@PathVariable Integer cust_id) {

        Optional<List<Order>> opt = custSvc.retrieveOrderById(cust_id);

        try {

            List<Order> orderList = opt.get();
            return ResponseEntity.status(HttpStatus.OK).body(orderList);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No order record found");
            // throw new ResourceNotFoundException("Order for selected Id=%d provided not found".formatted(cust_id));
        }
    }
    
}
