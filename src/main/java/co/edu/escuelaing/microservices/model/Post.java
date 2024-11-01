package co.edu.escuelaing.microservices.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Post extends PanacheMongoEntity {

    private User author;

    private String title;

    private String content;

    private Stream stream;

    public Post(User user, String title, String content, Stream stream) {
        this.author = user;
        this.title = title;
        this.content = content;
        this.stream = stream;
    }
}
