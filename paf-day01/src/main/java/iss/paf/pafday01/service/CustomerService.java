package iss.paf.pafday01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.paf.pafday01.model.Customer;
import iss.paf.pafday01.repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> retrieveAllCustomers() {

        return customerRepo.getAllCustomers();
    }
    
}
