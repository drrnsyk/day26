package vttp2022.workshop26.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.workshop26.models.Game;
import vttp2022.workshop26.repositories.BoardgameRepository;

@Service
public class BoardgameService {

    @Autowired
    private BoardgameRepository boardgameRepo;

    public List<Game> getAllGamesByName(Integer limit, Integer offset) {
        return boardgameRepo.getAllGamesByName(limit, offset);
    }

    public List<Game> getAllGamesByRank(Integer limit, Integer offset) {
        return boardgameRepo.getAllGamesByRank(limit, offset);
    }

    public Game getGameByGameObjectId(String game_id) {
        return boardgameRepo.getGameByGameObjectId(game_id);
    }
}
