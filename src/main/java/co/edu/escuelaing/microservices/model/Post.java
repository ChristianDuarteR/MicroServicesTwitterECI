package co.edu.escuelaing.microservices.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
@NoArgsConstructor
public class Post {

    @BsonProperty("postId")
    private String postId;

    @BsonProperty("content")
    private String content;

    @BsonProperty("createdAt")
    private String createdAt;

    // Constructor con BsonCreator y BsonProperty en cada parámetro
    @BsonCreator
    public Post(
            @BsonProperty("postId") String postId,
            @BsonProperty("content") String content,
            @BsonProperty("createdAt") String createdAt
    ) {
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
