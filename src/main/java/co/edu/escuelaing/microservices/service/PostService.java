package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.exception.TooMuchContentException;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.repository.PostRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class PostService {
    @Inject
    PostRepository postRepository;
    public void save(Post post) throws TooMuchContentException {
        if (post.getContent().length()> 140) throw new TooMuchContentException();
        else {
            postRepository.persist(post);
        }
    }
}
