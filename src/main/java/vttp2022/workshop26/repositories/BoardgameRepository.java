package vttp2022.workshop26.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.workshop26.models.Game;

@Repository
public class BoardgameRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> getAllGamesByName(Integer limit, Integer offset) {
        
        // criteria
        // no criteria

        // query
        final Pageable pageableRequest = PageRequest.of(offset, limit);
        Query query = new Query();
        query.with(pageableRequest);

        // returns
        List<Document> listOfDocument = mongoTemplate.find(query, Document.class, "games");
        List<Game> listOfGames = new LinkedList<>();
        for (int i = 0; i < listOfDocument.size(); i++) {
            listOfGames.add(Game.readDocumentCreateGame(listOfDocument.get(i)));
        }
        return listOfGames;

        // returns (using lamda for shorter, more efficient)
        // return mongoTemplate.find(query, Document.class, "games")
        //     .stream()
        //     .map(d -> Game.create(d))
        //     .toList();
    }

    
    public List<Game> getAllGamesByRank(Integer limit, Integer offset) {
        
        // criteria
        // no criteria

        // query
        final Pageable pageableRequest = PageRequest.of(offset, limit);
        Query query = new Query();
        query.with(pageableRequest).with(Sort.by(Sort.Direction.ASC, "ranking"));

        // returns
        List<Document> listOfDocument = mongoTemplate.find(query, Document.class, "games");
        List<Game> listOfGames = new LinkedList<>();
        for (int i = 0; i < listOfDocument.size(); i++) {
            listOfGames.add(Game.readDocumentCreateGame(listOfDocument.get(i)));
        }
        return listOfGames;

        // returns (using lamda for shorter, more efficient)
        // return mongoTemplate.find(query, Document.class, "games")
        //     .stream()
        //     .map(d -> Game.create(d))
        //     .toList();
    }

    public Game getGameByGameObjectId(String game_id) {

        // Find by object id
        ObjectId docId = new ObjectId(game_id);
        Document document = mongoTemplate.findById(docId, Document.class, "games");
        Game game = new Game();
        game = Game.readDocumentCreateGame(document);
        return game;

        // // criteria
        // Criteria criteria = Criteria.where("_id").is(game_id);

        // // query
        // Query query = new Query();
        // Query.query(criteria);

        // // returns
        // Game game = new Game();
        // game = mongoTemplate.findOne(query, Game.class, "games");
        // return game;
    }

}
