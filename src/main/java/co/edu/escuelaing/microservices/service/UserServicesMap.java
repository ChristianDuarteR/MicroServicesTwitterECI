package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserServicesMap implements UserService{
    @Inject
    UserRepository userRepository;


    @Override
    public User saveUser(String email, String userName, String fullName) {
        User newUser = new User(UUID.randomUUID().toString(),userName,fullName,email,new ArrayList<>());
        userRepository.persist(newUser);
        return newUser;
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
        return userRepository.findAll().list();
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
