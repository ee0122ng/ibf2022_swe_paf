package iss.ibf2022.pafday08workshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafday08workshop.exception.GameResourceNotFoundException;
import iss.ibf2022.pafday08workshop.model.Game;
import iss.ibf2022.pafday08workshop.payload.GameDetail;
import iss.ibf2022.pafday08workshop.payload.GamePayload;
import iss.ibf2022.pafday08workshop.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepo;

    public Optional<GamePayload> retrieveGames(Integer limit, Integer offset) {

        List<Document> gameList = gameRepo.getGames(limit, offset);

        if (gameList.size() > 0) {
            GamePayload payload = convertToPayload(gameList, limit, offset);
            return Optional.of(payload);
            
        } else {

            throw new GameResourceNotFoundException("Out of bound request");
        }

    }

    public Optional<GamePayload> retrieveGameByRank(Integer limit, Integer offset) {

        List<Document> gameList = gameRepo.getGamesByRank(limit, offset);

        if (gameList.size() > 0) {
            GamePayload payload = convertToPayload(gameList, limit, offset);
            return Optional.of(payload);

        } else {

            throw new GameResourceNotFoundException("Out of bound request");
        }
    }

    public Optional<GameDetail> retrieveGameById(String id) {
        
        List<Document> gameList = gameRepo.getGameById(id);

        if (gameList.size() > 0) {
            GameDetail gameDetail = convertToGameDetail(gameList);
            return Optional.of(gameDetail);

        } else {

            throw new GameResourceNotFoundException("Game id = '%s' provided not found.".formatted(id));
        }
    }

    public GamePayload convertToPayload(List<Document> gameList, Integer limit, Integer offset) {

        GamePayload payload = new GamePayload();

        List<Game> games = gameList.stream().map(d -> new Game(
            d.getInteger("gid"),
            d.getString("name")
        )).toList();

        payload.setGames(games);
        payload.setLimit(limit);
        payload.setOffset(offset);
        payload.setTotal(gameList.size());
        payload.setTimestamp(new Date());

        return payload;

    }

    public GameDetail convertToGameDetail(List<Document> gameList) {

        GameDetail gameDetail = new GameDetail();

        Document game = gameList.get(0);

        gameDetail.setGame_id(game.getInteger("gid"));
        gameDetail.setName(game.getString("name"));
        gameDetail.setRanking(game.getInteger("ranking"));
        gameDetail.setThumbnail(game.getString("image"));
        gameDetail.setTimestamp(new Date());
        gameDetail.setUrl(game.getString("url"));
        gameDetail.setUsers_rated(game.getInteger("users_rated"));
        gameDetail.setYear(game.getInteger("year"));
        
        return gameDetail;

    }
    
}
