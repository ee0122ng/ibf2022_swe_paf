package iss.paf.pafday05transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.paf.pafday05transaction.payload.BookReservationRequest;
import iss.paf.pafday05transaction.service.BookReservationService;

@RestController
@RequestMapping(path={"/api/books"})
public class BookAccountController {

    @Autowired
    BookReservationService bookAccSvc;

    @PostMapping
    public ResponseEntity<Object> reserveBooks (@RequestBody BookReservationRequest rq) {

        Boolean bReserveSuccessful = bookAccSvc.reserveBooks(rq.getBooks(), rq.getFullName());

        if (bReserveSuccessful) {

            rq.setMessage("Your reservation is successful!");
            return ResponseEntity.status(HttpStatus.OK).body(rq);

        } else {

            rq.setMessage("Your reservation is unsuccessful!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rq);

        }

    }
    
}
