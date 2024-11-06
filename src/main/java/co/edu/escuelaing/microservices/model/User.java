package co.edu.escuelaing.microservices.model;


import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;


import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@MongoEntity(collection = "User")
public class User {

    @BsonId
    private String id;

    @BsonProperty("userName")
    private String userName;

    @BsonProperty("fullName")
    private String fullName;

    @BsonProperty("email")
    private String email;

    @BsonProperty("streams")
    private List<Stream> streams;

    @BsonCreator
    public User(
            @BsonProperty("id") String id,
            @BsonProperty("userName") String userName,
            @BsonProperty("fullName") String fullName,
            @BsonProperty("email") String email,
            @BsonProperty("streams") List<Stream> streams
    ) {
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
