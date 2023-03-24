package iss.ibf2022.pafworkshopday03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafworkshopday03.model.Order;
import iss.ibf2022.pafworkshopday03.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    public List<Order> retrieveOrderById(String id) {

        return orderRepo.getOrderById(id);
    }
    
}
