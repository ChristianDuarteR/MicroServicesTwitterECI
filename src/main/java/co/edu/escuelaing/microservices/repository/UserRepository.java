package co.edu.escuelaing.microservices.repository;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.jose4j.jwk.Use;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{email:'?0'}")
    User findUserByEmail(String email);

}
