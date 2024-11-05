package co.edu.escuelaing.microservices.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Post{

    private String postId;
    private String content;
    private String createdAt;

    public Post(String id, String content, String createdAt) {
        this.postId = id;
        this.content = content;
        this.createdAt = createdAt;
    }

}
