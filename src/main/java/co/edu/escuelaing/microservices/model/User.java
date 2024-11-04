package co.edu.escuelaing.microservices.model;


import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;


import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@MongoEntity(collection = "User")
public class User{
    @BsonId
    private String id;
    private String userName;
    private String fullName;
    private String email;
    private List<Stream> streams;


    public User(String id, String userName, String fullName, String email, List<Stream> streams) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.streams = streams;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", streams=" + streams.toString() +
                '}';
    }
}
