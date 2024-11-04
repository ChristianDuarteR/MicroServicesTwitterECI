package co.edu.escuelaing.microservices.repository;


import co.edu.escuelaing.microservices.model.User;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
    public User findUserByEmail(String email){
        return find("email", email).firstResult();
    }

}
