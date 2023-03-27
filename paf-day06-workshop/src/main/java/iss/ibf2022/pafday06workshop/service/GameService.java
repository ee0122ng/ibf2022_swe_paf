package iss.ibf2022.pafday06workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.ibf2022.pafday06workshop.model.Game;
import iss.ibf2022.pafday06workshop.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepo;

    public List<String> retrieveAllGame(Integer limit, Integer skip) {
        
        List<String> games = gameRepo.getAllGame(limit, skip);

        return games;
    }

    public List<String> retrieveGameByRank(Integer limit, Integer skip) {

        return gameRepo.getGameByRank(limit, skip);
    }

    public Game retrieveGameById(Integer id) {

        return gameRepo.getGameById(id);
    }
    
}
