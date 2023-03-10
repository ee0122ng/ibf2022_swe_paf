package iss.paf.pafday01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafday01.model.Customer;
import iss.paf.pafday01.model.Order;
import iss.paf.pafday01.repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> retrieveAllCustomers() {

        return customerRepo.getAllCustomers();
    }

    public List<Customer> retrieveAllCustomerByLimitOffset(Integer limit, Integer offset) {

        return customerRepo.getAllCustomersWithLimitOffset(limit, offset);
    }
    
    public Customer retrieveCustomerById(Integer id) {

        return customerRepo.getCustomerById(id);
    }

    public List<Order> retrieveCustomerOrder(Integer id) {

        return customerRepo.getCustomerOrder(id);
    }

}
