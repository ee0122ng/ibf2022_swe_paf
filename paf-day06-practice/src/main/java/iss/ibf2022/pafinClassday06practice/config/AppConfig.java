package iss.ibf2022.pafinClassday06practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {

    @Value("${mongo.url}")
    private String mongoUrl;
    
    @Bean
    public MongoTemplate createMongoTemplate() {
        // create a mongo client
        MongoClient client = MongoClients.create(mongoUrl);

        // create a mongo template
        // becareful of the database name, check if before instantiate
        MongoTemplate template = new MongoTemplate(client, "restaurants");

        return template;
    }

    
}
