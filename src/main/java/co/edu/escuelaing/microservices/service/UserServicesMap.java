package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesMap implements UserService{
    @Autowired
    UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Stream newStream(String userId, Stream stream) {
        return null;
    }

    @Override
    public Post newPostToStream(String userId, String streamId, Post post) {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void updateUser(String userId, User user) {

    }

    @Override
    public void updatePost(String userId, String streamId, Post post) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void deleteStream(String userId, String streamId) {

    }

    @Override
    public void deletePost(String userId, String streamId, String postId) {

    }
}
