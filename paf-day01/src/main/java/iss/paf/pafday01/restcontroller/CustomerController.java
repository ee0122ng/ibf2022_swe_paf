package iss.paf.pafday01.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iss.paf.pafday01.model.Customer;
import iss.paf.pafday01.model.Order;
import iss.paf.pafday01.service.CustomerService;

@RestController
@RequestMapping(path={"/api/customers"})
public class CustomerController {

    @Autowired
    CustomerService customerSvc;

    @GetMapping()
    public List<Customer> getCutomers() {
        return customerSvc.retrieveAllCustomers();
    }
    
    @GetMapping(path={"/filter"})
    public List<Customer> getCustomersByLimitOffset(@RequestParam(required=true) MultiValueMap<String, String> query) {

        Integer limit = Integer.valueOf(query.getFirst("limit"));
        Integer offset = Integer.valueOf(query.getFirst("offset"));

        return customerSvc.retrieveAllCustomerByLimitOffset(limit, offset);

    }

    @GetMapping(path={"/{id}"})
    public Customer getCustomerById(@PathVariable String id) {

        Integer idValue = Integer.valueOf(id);

        return customerSvc.retrieveCustomerById(idValue);
    }

    @GetMapping(path={"/{customerId}/orders"})
    public List<Order> getCustomerOrder(@PathVariable String customerId) {

        Integer custId = Integer.valueOf(customerId);

        return customerSvc.retrieveCustomerOrder(custId);
    }
}
