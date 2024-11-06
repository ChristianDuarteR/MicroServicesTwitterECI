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

    @BsonProperty("author")
    private String author;

    // Constructor con BsonCreator y BsonProperty en cada parámetro
    @BsonCreator
    public Post(
            @BsonProperty("postId") String postId,
            @BsonProperty("content") String content,
            @BsonProperty("createdAt") String createdAt,
            @BsonProperty("author") String email

    ) {
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.author = email;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
