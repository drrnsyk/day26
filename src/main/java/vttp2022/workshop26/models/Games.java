package vttp2022.workshop26.models;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Games {
    
    // create a model to display the endpoint
    private List<Game> listOfGames;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private DateTime timeStamp;

    public List<Game> getListOfGames() {
        return listOfGames;
    }
    public void setListOfGames(List<Game> listOfGames) {
        this.listOfGames = listOfGames;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public DateTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(DateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    // helper method to convert from model to json
    public JsonObject toJSON() {
        // List<JsonObject> listOfjo = this.getListOfGames()
        //         .stream()
        //         .map(g -> g.readModelCreateJsonObj())
        //         .toList();
        
        // convert list of games into a list of json object
        List<JsonObject> listOfjo = new LinkedList<>();
        List<Game> listOfGames = this.getListOfGames();
        for (int i = 0; i < listOfGames.size(); i++) {
            listOfjo.add(listOfGames.get(i).readModelCreateJsonObj());
        }

        // create the json object with the list of games in json string
        return Json.createObjectBuilder()
                .add("games", listOfjo.toString())
                .add("offset", getOffset())
                .add("limit", getLimit())
                .add("total", getTotal())
                .add("timestamp", getTimeStamp().toString())
                .build();
    }


    // helper method to convert from model to json with only gid and name field
    public JsonObject toJSONpartial() {
        // List<JsonObject> listOfjo = this.getListOfGames()
        //         .stream()
        //         .map(g -> g.readModelCreateJsonObj())
        //         .toList();
        
        // convert list of games into a list of json object
        List<JsonObject> listOfjo = new LinkedList<>();
        List<Game> listOfGames = this.getListOfGames();
        for (int i = 0; i < listOfGames.size(); i++) {
            listOfjo.add(listOfGames.get(i).readModelCreateJsonObjPartial());
        }

        // create the json object with the list of games in json string
        return Json.createObjectBuilder()
                .add("games", listOfjo.toString())
                .add("offset", getOffset())
                .add("limit", getLimit())
                .add("total", getTotal())
                .add("timestamp", getTimeStamp().toString())
                .build();
    }

    
}
