package iss.ibf2022.pafday06workshop.restcontroller;

import java.time.LocalDateTime;
import java.util.List;
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

import iss.ibf2022.pafday06workshop.exception.ResourceNotFoundException;
import iss.ibf2022.pafday06workshop.model.Game;
import iss.ibf2022.pafday06workshop.payload.GamePayload;
import iss.ibf2022.pafday06workshop.service.GameService;

@RestController
@RequestMapping(path={""})
public class GameController {
    
    @Autowired
    GameService gameSvc;

    @GetMapping(path={"/games"})
    public ResponseEntity<GamePayload> getAllGame(@RequestParam MultiValueMap<String, String> input) {

        Integer limit = 0;
        Integer skip = 0;

        if (null == input.getFirst("limit") || input.getFirst("limit").isEmpty()) {
            limit = 25;
        } else {
            limit = Integer.valueOf(input.getFirst("limit"));
        }

        if (null == input.getFirst("skip") || input.getFirst("skip").isEmpty()) {
            skip = 0;
        } else {
            skip = Integer.valueOf(input.getFirst("skip"));
        }

        List<String> games = gameSvc.retrieveAllGame(limit, skip);
        GamePayload payload = new GamePayload().createGamePayload(games, skip, limit);

        return Optional.of(new ResponseEntity<>(payload, HttpStatus.OK)).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping(path={"/games/rank"})
    public ResponseEntity<GamePayload> getGameByRank(@RequestParam MultiValueMap<String, String> input) {

        Integer limit = 0;
        Integer skip = 0;

        if (null == input.getFirst("limit") || input.getFirst("limit").isEmpty()) {
            limit = 25;
        } else {
            limit = Integer.valueOf(input.getFirst("limit"));
        }

        if (null == input.getFirst("skip") || input.getFirst("skip").isEmpty()) {
            skip = 0;
        } else {
            skip = Integer.valueOf(input.getFirst("skip"));
        }

        List<String> games = gameSvc.retrieveGameByRank(limit, skip);
        GamePayload payload = new GamePayload().createGamePayload(games, skip, limit);

        return Optional.of(new ResponseEntity<>(payload, HttpStatus.OK)).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping(path={"/game/{id}"})
    public ResponseEntity<Game> getGameById(@PathVariable Integer id) {

        Game game = gameSvc.retrieveGameById(id);
        game.setTimestamp(LocalDateTime.now());

        return Optional.of(new ResponseEntity<>(game, HttpStatus.OK)).orElseThrow(ResourceNotFoundException::new);
    }
}
