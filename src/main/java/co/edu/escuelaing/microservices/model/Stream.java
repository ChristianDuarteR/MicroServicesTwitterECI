package co.edu.escuelaing.microservices.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@MongoEntity(collection = "Stream")
public class Stream {

    @BsonProperty("streamId") // Anotación para mapear el campo en MongoDB
    private String streamId;

    @BsonProperty("posts") // Anotación para mapear el campo en MongoDB
    private List<Post> posts;


    // Constructor con BsonCreator y BsonProperty
    @BsonCreator
    public Stream(
            @BsonProperty("streamId") String streamId,
            @BsonProperty("posts") List<Post> posts
    ) {
        this.streamId = streamId;
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
