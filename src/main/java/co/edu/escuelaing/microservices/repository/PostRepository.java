package co.edu.escuelaing.microservices.repository;

import co.edu.escuelaing.microservices.model.Post;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheMongoRepository<Post> {
}
