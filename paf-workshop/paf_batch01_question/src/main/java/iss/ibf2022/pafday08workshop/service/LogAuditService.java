package iss.ibf2022.pafday08workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafday08workshop.payload.TransferPayload;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class LogAuditService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void storeTransaction(TransferPayload payload) {
        
        JsonObject json = convertToJson(payload);

        redisTemplate.opsForHash().put("transactionRecord", json.getString("transactionId"), json.toString());

    }

    public JsonObject convertToJson(TransferPayload payload) {

        JsonObject json = Json.createObjectBuilder()
            .add("transactionId", payload.getTransactionId())
            .add("date", payload.getDate().toString())
            .add("from_account", payload.getAccountFromId())
            .add("to_account", payload.getAccountToId())
            .add("amount", payload.getAmount())
            .build();

        return json;

    }

    
}
