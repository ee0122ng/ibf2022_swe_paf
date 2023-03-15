package iss.paf.pafday05transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.paf.pafday05transaction.payload.TransferRequest;
import iss.paf.pafday05transaction.service.BankAccountService;

@RestController
@RequestMapping(path={"/api/accounts"})
public class BankAccountController {

    @Autowired
    BankAccountService bankAccSvc;

    @PostMapping()
    public ResponseEntity<Object> transferMoney(@RequestBody TransferRequest tr) {

        Boolean bTransferred = false;

        bTransferred = bankAccSvc.transferMoney(tr.getAccFromId(), tr.getAccToId(), tr.getAmount());

        if (bTransferred) {

            tr.setMessage("Your transfer request is successful!");
            return ResponseEntity.status(HttpStatus.OK).body(tr);

        } else {

            tr.setMessage("Your transfer request is unsuccessful!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tr);
        }
    }
    
}
