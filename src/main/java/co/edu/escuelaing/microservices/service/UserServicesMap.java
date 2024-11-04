package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.dto.PostDTO;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.stream;

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
        User user = userRepository.findUserByEmail(email);
        Stream stream = new Stream(UUID.randomUUID().toString(), new ArrayList<Post>());
        user.getStreams().add(stream);
        userRepository.update(user);
        return stream;
    }

    @Override
    public Stream getStreamId( String streamId, String email ){
        User user = userRepository.findUserByEmail(email);
        List<Stream> streams = user.getStreams();
        return getStream(streamId, streams);
    }

    private Stream getStream (String streamId, List<Stream> streams){

        Stream streamToReturn = null;
        for (Stream stream : streams) {
            System.out.println("STREAM: " + stream);
            if (streamId.equals(stream.getStreamId())) {
                 streamToReturn = stream;
            }
        }
        return streamToReturn;
    }


    @Override
    public Post newPost(PostDTO postDTO){
        return new Post(UUID.randomUUID().toString(),postDTO.getContent(), postDTO.getCreatedAt());
    }

    @Override
    public void newPostToStream(String email, String streamId, Post post) {
        User user = userRepository.findUserByEmail(email);
        Stream stream = getStream(streamId, user.getStreams());
        stream.getPosts().add(post);
        userRepository.update(user);
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
