package iss.ibf2022.pafworkshopday03.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.ibf2022.pafworkshopday03.exception.ResourceNotFoundException;
import iss.ibf2022.pafworkshopday03.model.Order;
import iss.ibf2022.pafworkshopday03.service.OrderService;

@RestController
@RequestMapping(path={"/api"})
public class OrderRestController {

    @Autowired
    OrderService orderSvc;

    @GetMapping(path={"/order/total/{order_id}"})
    public ResponseEntity<Object> getOrderByCustomerId(@PathVariable String order_id) {

        List<Order> orderList = orderSvc.retrieveOrderById(order_id);
        ResponseEntity<Object> rep = ResponseEntity.status(HttpStatus.OK).body(orderList);

        return Optional.of(rep).orElseThrow(ResourceNotFoundException::new);

    }
    
}
