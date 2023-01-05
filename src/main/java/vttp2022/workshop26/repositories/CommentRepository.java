package vttp2022.workshop26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import vttp2022.workshop26.models.Comment;

@Repository
public class CommentRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    // search by text 
    public List<Comment> searchCommentByText(List<String> includes, List<String> excludes, Integer limit, Integer offset) {

        TextCriteria tc = TextCriteria.forDefaultLanguage()
                .matchingAny(includes.toArray(new String[includes.size()])) // converting from list of string to a string array
                .notMatchingAny(excludes.toArray(new String[excludes.size()])); // converting from list of string to a string array

        TextQuery tq = (TextQuery) TextQuery.queryText(tc)
                .includeScore("score") // need to supplement more things to the text query
                .sortByScore() // sorting
                .limit(limit) // pagination
                .skip(offset); 

        return mongoTemplate.find(tq, Document.class, "comments")
                .stream() // stream the result which is a list of document to build the model for each document object retrived
                .map(c -> Comment.create(c)) // build the model from the document object
                .toList();
    }

}
