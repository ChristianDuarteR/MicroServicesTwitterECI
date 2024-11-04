package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServicesMap implements UserService{
    @Autowired
    UserRepository userRepository;


    @Override
    public User saveUser(String email, String userName, String fullName) {
        return userRepository.save(new User(UUID.randomUUID().toString(),userName,fullName,email,new ArrayList<>()));
    }

    @Override
    public Stream newStream(String email) {
        return null;
    }

    @Override
    public Post newPostToStream(String email, String streamId, Post post) {
        return null;
    }

    @Override
    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public void deleteStream(String email, String streamId) {

    }

    @Override
    public void deletePost(String email, String streamId, String postId) {

    }
}
