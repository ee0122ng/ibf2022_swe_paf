package iss.paf.pafday03.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.paf.pafday03.model.Order;
import iss.paf.pafday03.repository.OrderRepository;

@RestController 
@RequestMapping(path={"/api/orders"})
public class OrderRestController {

    @Autowired
    OrderRepository orderRepo;

    @GetMapping(path={"/{id}"})
    public ResponseEntity<Object> getOrderDetailsById(@PathVariable Integer id) {

        List<Order> orderList = orderRepo.getOrderDetailsById(id);

        if (orderList.size() > 0) {

            return ResponseEntity.status(HttpStatus.OK).body(orderList); 
        }

        return ResponseEntity.status(HttpStatus.OK).body("Record not found for id = %d".formatted(id));
    }
    
}
