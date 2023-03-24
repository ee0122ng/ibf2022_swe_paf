package iss.ibf2022.pafworkshopday04.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.ibf2022.pafworkshopday04.exception.MissingDetailsException;
import iss.ibf2022.pafworkshopday04.payload.RequestPayload;
import iss.ibf2022.pafworkshopday04.service.OrderService;

@RestController
@RequestMapping(path={"/api"})
public class OrderRestController {

    @Autowired
    OrderService orderSvc;

    @PostMapping(path={"/order"})
    public ResponseEntity<Object> createNewOrderRecord(@RequestBody MultiValueMap<String, String> form) {

        RequestPayload payload = orderSvc.insertNewOrder(form);
        ResponseEntity<Object> rep = ResponseEntity.status(HttpStatus.CREATED).body(payload);
        
        return Optional.of(rep).orElseThrow(MissingDetailsException::new);
        
    }
    
}