package dev.pioruocco.repository.impl;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.pioruocco.model.entity.PostEntity;
import dev.pioruocco.repository.SearchRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

    //Instance of MongoClient for interacting with MongoDB.
    private final MongoClient client;
    //Instance of MongoConverter for converting between Java objects
    private final MongoConverter converter;

    @Autowired
    public SearchRepositoryImpl(MongoClient client, MongoConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    /*
    Takes a text parameter for searching posts. Returns a List<PostEntity>.

    Creates an empty list posts to store results. Retrieves the database named "job-hire" and the collection named "JobPost" from the MongoClient.

    Constructs an aggregation pipeline using AggregateIterable:
        -> First stage: Performs a full-text search using the $search operator.
           Searches within the "techs", "desc", and "profile" fields of documents.
           Uses the provided text as the search query.
        -> Second stage (optional): Sorts the results by "exp" field in ascending order (1L).
        -> Third stage (optional): Limits the number of returned documents to 5 using $limit.
    Iterates over the aggregation results and converts each document to a PostEntity object using the MongoConverter.

    Returns the final list of PostEntity objects.
     */
    @Override
    public List<PostEntity> findByText(String text) {

        final List<PostEntity> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("job-hire");
        MongoCollection<Document> collection = database.getCollection("JobPost");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(converter.read(PostEntity.class,doc)));

        return posts;
    }

}
