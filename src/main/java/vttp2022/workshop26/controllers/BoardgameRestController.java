package vttp2022.workshop26.controllers;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.workshop26.models.Comment;
import vttp2022.workshop26.models.Game;
import vttp2022.workshop26.models.Games;
import vttp2022.workshop26.services.BoardgameService;

@Controller
@RequestMapping("/api")
public class BoardgameRestController {
    
    @Autowired
    private BoardgameService boardgameSvc;

    @GetMapping(path = "/games", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllGamesByName (@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
    // get all games by name, obtain parameters limit and offset from URL through postman injection
        
        if (null == limit)
            limit = 25;

        if (null == offset)
            offset = 0;

        // retrive the list of games in database through service
        List<Game> listOfGames = boardgameSvc.getAllGamesByName(limit, offset);
        

        // build model to display endpoint
        Games games = new Games();
        games.setListOfGames(listOfGames);
        games.setOffset(offset);
        games.setLimit(limit);
        games.setTotal(listOfGames.size());
        games.setTimeStamp(DateTime.now());

        // read from model and create json object
        JsonObject jo = Json.createObjectBuilder()
                .add("boardgames", games.toJSONpartial())
                .build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jo.toString());
    }

    @GetMapping(path = "/games/rank", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllGamesByRank (@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
        // get all games by name, obtain parameters limit and offset from URL through postman injection
    
        if (null == limit)
        limit = 25;

        if (null == offset)
            offset = 0;

        // retrive the list of games in database through service
        List<Game> listOfGames = boardgameSvc.getAllGamesByRank(limit, offset);

        // build model to display endpoint
        Games games = new Games();
        games.setListOfGames(listOfGames);
        games.setOffset(offset);
        games.setLimit(limit);
        games.setTotal(listOfGames.size());
        games.setTimeStamp(DateTime.now());

        // read from model and create json object
        JsonObject jo = Json.createObjectBuilder()
                .add("boardgames", games.toJSON())
                .build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jo.toString());
    }

    @GetMapping(path = "/game/{game_id}")
    public ResponseEntity<String> getGameByGameObjectId (@PathVariable String game_id) {
        System.out.println(game_id);
        // retrive game in database through service
        Game game = boardgameSvc.getGameByGameObjectId(game_id);

        // read from model and create json object
        JsonObject jo = Json.createObjectBuilder()
                .add("game", game.readModelCreateJsonObj())
                .build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(jo.toString());
    }

    /*
        remember to create the index
        db.comments.createIndex({c_text: "text"})

        postman:
        localhost:8080/api/comment?keyword=players -4
        ** need to leave a space because the code split by " "
    */
    @GetMapping(path = "/comment")
    public ResponseEntity<String> getSearchCommentByText(@RequestParam String keyword, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {

        if (null == limit)
            limit = 25;

        if (null == offset)
            offset = 0;

        List<Comment> listOfComments = boardgameSvc.searchCommentByText(keyword, limit, offset);
        JsonArray jsonArr = null;
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        
        for (Comment c : listOfComments)
            arrBuilder.add(c.toJSON());

        jsonArr = arrBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArr.toString());
    }
}
