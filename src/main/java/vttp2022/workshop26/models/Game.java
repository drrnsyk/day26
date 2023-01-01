package vttp2022.workshop26.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    // use mongo shell to query the collection to get the schema
    // create Game model accordingly
    // game = model , games = collection in mongodb

    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;

    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(Integer users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


    // helper method, read from json document and convert to model
    public static Game readDocumentCreateGame(Document d) {
        Game game = new Game();
        game.setGid(d.getInteger("gid"));
        game.setName(d.getString("name"));
        game.setYear(d.getInteger("year"));
        game.setRanking(d.getInteger("ranking"));
        game.setUsers_rated(d.getInteger("users_rated"));
        game.setUrl(d.getString("url"));
        game.setImage(d.getString("image"));
        return game;
    }

    // helper method, read from model and create json object, toJSON
    public JsonObject readModelCreateJsonObj() {
        return Json.createObjectBuilder()
            .add("gid", getGid())
            .add("name", getName())
            .add("year", getYear())
            .add("ranking", getRanking())
            .add("users_rated", getUsers_rated() != null ? getUsers_rated() : 0) 
            // ternary operator: variable = (condition) ? expressionTrue : expressionFalse;
            .add("url", getUrl())
            .add("image", getImage())
            .build();
    }

        // helper method, read from model and create json object, toJSON with only gid and name field
        public JsonObject readModelCreateJsonObjPartial() {
            return Json.createObjectBuilder()
                .add("gid", getGid())
                .add("name", getName())
                .build();
        }
    
}
