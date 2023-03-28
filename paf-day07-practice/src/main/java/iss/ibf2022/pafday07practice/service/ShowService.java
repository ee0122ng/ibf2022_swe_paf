package iss.ibf2022.pafday07practice.service;

import java.io.StringReader;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import iss.ibf2022.pafday07practice.model.Feedback;
import iss.ibf2022.pafday07practice.model.Show;
import iss.ibf2022.pafday07practice.repository.ShowRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepo;

    public List<String> getUniqueShowName() {
        
        return showRepo.getListOfShowName();
    }

    public Document getShowDetailsByName(String name) {

        return showRepo.getShowByName(name);
    }

    public Show convertDocumentToShow(Document doc) {

        JsonReader jReader = Json.createReader(new StringReader(doc.toJson()));
        JsonObject jShow = jReader.readObject();
        
        Show show = new Show();
        show.setId(jShow.getInt("id"));
        show.setName(jShow.getString("name"));
        show.setLanguage(jShow.getString("language"));
        show.setGenres(jShow.getJsonArray("genres").stream().map(j -> j.toString()).toList());
        show.setRuntime(jShow.getInt("runtime"));
        show.setRating(jShow.getJsonObject("rating").getJsonNumber("average").doubleValue());
        show.setSummary(jShow.getString("summary"));

        return show;
    }

    public Long updateFeedback(Feedback feedback) {
        
        return showRepo.upsertFeedback(feedback);
    }
    
}
