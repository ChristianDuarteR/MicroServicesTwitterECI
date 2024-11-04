package co.edu.escuelaing.microservices.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@MongoEntity(collection = "Stream")
public class Stream {
    private String streamId;
    private List<Post> posts;

    public Stream(String id, List<Post> posts) {
        this.streamId = id;
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "streamId='" + streamId + '\'' +
                ", posts=" + posts +
                '}';
    }
}
