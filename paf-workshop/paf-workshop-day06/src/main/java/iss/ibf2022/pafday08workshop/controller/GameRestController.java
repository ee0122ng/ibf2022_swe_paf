package iss.ibf2022.pafday08workshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iss.ibf2022.pafday08workshop.payload.GameDetail;
import iss.ibf2022.pafday08workshop.payload.GamePayload;
import iss.ibf2022.pafday08workshop.service.GameService;

@RestController
@RequestMapping()
public class GameRestController {

    @Autowired
    GameService gameSvc;

    @GetMapping(path={"/games"})
    public ResponseEntity<Object> getGames(@RequestParam MultiValueMap<String, String> req) {

        try {
            Integer limit = Integer.valueOf(req.getFirst("limit"));
            Integer offset = Integer.valueOf(req.getFirst("offset"));

            if(null == limit) {
                limit = 25;
            }
            if (null == offset) {
                offset = 0;
            }

            Optional<GamePayload> opt = gameSvc.retrieveGames(limit, offset);
            try {
                GamePayload payload = opt.get();
                return ResponseEntity.status(HttpStatus.OK).body(payload);

            } catch (Exception ex) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
            }


        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());

        }
    }


    @GetMapping(path={"/games/rank"})
    public ResponseEntity<Object> getGamesByRank(@RequestParam MultiValueMap<String, String> req) {

        try {
            Integer limit = Integer.valueOf(req.getFirst("limit"));
            Integer offset = Integer.valueOf(req.getFirst("offset"));

            if(null == limit) {
                limit = 25;
            }
            if (null == offset) {
                offset = 0;
            }

            Optional<GamePayload> opt = gameSvc.retrieveGameByRank(limit, offset);
            try {
                GamePayload payload = opt.get();
                return ResponseEntity.status(HttpStatus.OK).body(payload);

            } catch (Exception ex) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
            }


        } catch (NumberFormatException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());

        }
    }

    @GetMapping(path={"/game/{id}"})
    public ResponseEntity<Object> getGameBYId(@PathVariable String id) {

        try {
            Optional<GameDetail> opt = gameSvc.retrieveGameById(id);
            GameDetail gameDetail = opt.get();
            return ResponseEntity.status(HttpStatus.OK).body(gameDetail);

        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
        }
    }
    
}
