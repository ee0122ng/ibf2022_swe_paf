package ibf2022.batch2.paf.serverstub.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.paf.serverstub.Repository.TransactionRepository;
import jakarta.json.JsonObject;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository tranRepo;

    public Document insertTransaction(JsonObject json) {

        return tranRepo.insertTransaction(json);
        
    }

    
}
