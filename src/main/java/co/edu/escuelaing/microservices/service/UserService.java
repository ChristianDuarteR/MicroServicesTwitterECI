package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;

import java.util.List;

public interface UserService {

    /*CREATE*/
    User saveUser(User user);
    Stream newStream(String userId, Stream stream);
    Post newPostToStream(String userId, String streamId, Post post);
    /*READ*/
    User getUser(String userId);
    List<User> getUsers();
    /*UPDATE*/
    void updateUser(String userId,User user);
    void updatePost(String userId, String streamId, Post post);
    /**/
    void deleteUser(String userId);
    void deleteStream(String userId, String streamId);
    void deletePost(String userId,String streamId,String postId);
}
