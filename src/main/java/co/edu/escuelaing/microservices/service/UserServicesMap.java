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

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

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
        List<Stream> streams = user.getStreams();
        streams.add(stream);
        return stream;
    }

    @Override
    public Stream getStreamId( String idString, String email ){
        User user = userRepository.findUserByEmail(email);
        List<Stream> streams = user.getStreams(); 
        Stream stream= getStream(idString, streams); 
        return stream;
    }

    private Stream getStream (String idString, List<Stream> lista){
        
        for (int i=0; i<lista.size(); i++){
            if (idString.equals(lista.get(i).getStreamId())){

                return lista.get(i);
            }
        }
         return null;
    }


    @Override
    public Post newPost(PostDTO postDTO){
        return new Post(UUID.randomUUID().toString(),postDTO.getContent(), postDTO.getCreatedAt());
    }

    @Override
    public void newPostToStream(String email, String streamId, Post post) {
        User user = userRepository.findUserByEmail(email);
        Stream stream = user.getStreams().get(Integer.parseInt(streamId));
        List<Post> posts = stream.getPosts();
        posts.add(post);
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
