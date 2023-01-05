package vttp2022.workshop26.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.workshop26.models.Comment;
import vttp2022.workshop26.models.Game;
import vttp2022.workshop26.repositories.BoardgameRepository;
import vttp2022.workshop26.repositories.CommentRepository;

@Service
public class BoardgameService {

    @Autowired
    private BoardgameRepository boardgameRepo;

    @Autowired
    private CommentRepository commentRepo;

    public List<Game> getAllGamesByName(Integer limit, Integer offset) {
        return boardgameRepo.getAllGamesByName(limit, offset);
    }

    public List<Game> getAllGamesByRank(Integer limit, Integer offset) {
        return boardgameRepo.getAllGamesByRank(limit, offset);
    }

    public Game getGameByGameObjectId(String game_id) {
        return boardgameRepo.getGameByGameObjectId(game_id);
    }

    public List<Comment> searchCommentByText(String s, Integer limit, Integer offset) {

        // construct the inclusive word and exclusive word
        // if it is negative(-) excludes
        List<String> includes = new LinkedList<>();
        List<String> excludes = new LinkedList<>();

        // doing string split to obtain the included keyword or the excluded keyword
        for (String w : s.split(" ")) {
            if (w.startsWith("-")) {
                String[] exW = w.split("-");
                excludes.add(exW[1]);
            } else {
                includes.add(w);
            }
        }

        return commentRepo.searchCommentByText(includes, excludes, limit, offset);
    }
}
