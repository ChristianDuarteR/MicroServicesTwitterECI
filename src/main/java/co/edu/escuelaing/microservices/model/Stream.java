package co.edu.escuelaing.microservices.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@MongoEntity(collection = "Stream")
public class Stream {
    private String streamId;
    private List<Post> posts;

    public Stream(String id, List<Post> posts) {
        this.streamId = id;
        this.posts = posts;
    }
}
