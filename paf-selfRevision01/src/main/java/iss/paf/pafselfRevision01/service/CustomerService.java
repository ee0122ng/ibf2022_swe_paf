package iss.paf.pafselfRevision01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafselfRevision01.model.Customer;
import iss.paf.pafselfRevision01.model.Order;
import iss.paf.pafselfRevision01.repository.CustomerRepository;
import iss.paf.pafselfRevision01.repository.OrderRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderRepository orderRepo;

    public List<Customer> retrieveCustomersWithLimitOffset(Integer limit, Integer offset) {

        return customerRepo.getCustomersWithLimitOffset(limit, offset);
    }

    public Optional<Customer> retrieveCustomerById(Integer id) {

        return customerRepo.getCustomerById(id);
    }

    public Optional<List<Order>> retrieveOrderByCustomerId(Integer id) {

        return orderRepo.getOrderByCustomerId(id);
    }
    
}
