package co.edu.escuelaing.microservices.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Stream {
    @Id
    private String streamId;
    private List<Post> posts;


    public Stream(String id, List<Post> posts) {
        this.streamId = id;
        this.posts = posts;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
