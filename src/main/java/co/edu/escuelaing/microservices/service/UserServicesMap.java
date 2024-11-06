package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.dto.PostDTO;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.text.SimpleDateFormat;
import java.util.*;

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
            if (Objects.equals(streamId, stream.getStreamId())) {
                 streamToReturn = stream;
                 break;
            }
        }
        return streamToReturn;
    }


    @Override
    public Post newPost(String email, PostDTO postDTO){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = formatter.format(date);

        return new Post(UUID.randomUUID().toString(),postDTO.getContent(), dateString, email);
    }


    @Override
    public void newPostToStream(String email, String streamId, Post post) {
        System.out.println(streamId);
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
