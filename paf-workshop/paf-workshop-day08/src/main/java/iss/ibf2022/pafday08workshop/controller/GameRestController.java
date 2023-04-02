package iss.ibf2022.pafday08workshop.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iss.ibf2022.pafday08workshop.model.Game;
import iss.ibf2022.pafday08workshop.payload.RequestPayload;
import iss.ibf2022.pafday08workshop.service.GameService;

@RestController
@RequestMapping(path={""})
public class GameRestController {

    @Autowired
    GameService gameSvc;

    @GetMapping(path={"/game/{id}/reviews"})
    public ResponseEntity<Object> getGameById(@PathVariable String id) {

        List<Document> docs = gameSvc.retrieveGameById(id);

        if (docs.size() > 0) {
            List<Game> games = gameSvc.convertToGame(docs);
            return ResponseEntity.status(HttpStatus.OK).body(games);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game id = '%s' provided not found".formatted(id));
        }
    }

    @GetMapping(path={"/games/highest"})
    public ResponseEntity<Object> getHighestRatedGameList() {

        Boolean ascending = false;
        RequestPayload payload = gameSvc.retrieveRatedGameList(ascending);

        return ResponseEntity.status(HttpStatus.OK).body(payload);
    }


    @GetMapping(path={"/games/lowest"})
    public ResponseEntity<Object> getLowestRatedGameList() {

        Boolean ascending = true;
        RequestPayload payload = gameSvc.retrieveRatedGameList(ascending);

        return ResponseEntity.status(HttpStatus.OK).body(payload);
    }

    
}
