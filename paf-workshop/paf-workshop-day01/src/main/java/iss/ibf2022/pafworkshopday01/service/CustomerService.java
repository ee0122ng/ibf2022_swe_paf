package iss.ibf2022.pafworkshopday01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafworkshopday01.model.Customer;
import iss.ibf2022.pafworkshopday01.model.Order;
import iss.ibf2022.pafworkshopday01.repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo custRepo;

    private Integer limit = 5;
    private Integer offset = 0;

    public Optional<List<Customer>> retrieveAll(String limit, String offset) {

        if (null != limit) {

            this.limit = Integer.valueOf(limit);

        }

        if (null != offset) {

            this.offset = Integer.valueOf(offset);
        }

        return custRepo.getAll(this.limit, this.offset);
    }

    public Optional<Customer> retrieveById(Integer id) {

        return custRepo.getById(id);
    }

    public Optional<List<Order>> retrieveOrderById(Integer id) {

        return custRepo.getOrderById(id);
    }
    
}
