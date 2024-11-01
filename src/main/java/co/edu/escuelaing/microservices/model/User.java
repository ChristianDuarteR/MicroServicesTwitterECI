package co.edu.escuelaing.microservices.model;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends PanacheMongoEntity {

    private String username;

    private String password;

    private List<Post> posts;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
