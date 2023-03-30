package ibf2022.batch2.paf.serverstub.Repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class TransactionRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    /*
     * db.records.insert({
     *  id: id,
     *  transaction: {
     *      from: fred,
     *      to: barney,
     *      amount: 100.0
     *  }
     * })
     */
    public Document insertTransaction(JsonObject json) {

        Document toInsert = Document.parse(json.toString());

        Document doc = mongoTemplate.insert(toInsert, "records");

        return doc;
    }
    
}
