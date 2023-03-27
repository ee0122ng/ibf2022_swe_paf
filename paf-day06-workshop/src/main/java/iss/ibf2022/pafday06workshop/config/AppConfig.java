package iss.ibf2022.pafday06workshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {

    @Value("${mongo.url}")
    private String url;

    @Bean
    public MongoTemplate createMongoTemplate(){

        MongoClient client = MongoClients.create(url);

        return new MongoTemplate(client, "boardgames");
    }
    
}
