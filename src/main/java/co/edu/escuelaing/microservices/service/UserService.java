package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.dto.PostDTO;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;

import java.util.List;

public interface UserService {

    /*CREATE*/
    User saveUser(String email,String userName,String fullName);
    Stream newStream(String email);

    Post newPost(PostDTO postDTO);

    void newPostToStream(String email, String streamId, Post post);


    /*READ*/
    User getUser(String email);

    Stream getStreamId(String idString, String email ); 
    List<User> getUsers();
    /*UPDATE (OMITIDO)*/
    /*DELETE*/
    void deleteUser(String email);
    void deleteStream(String email, String streamId);
    void deletePost(String email,String streamId,String postId);
}
