package co.edu.escuelaing.microservices.model;


import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;


import java.util.List;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }
}
