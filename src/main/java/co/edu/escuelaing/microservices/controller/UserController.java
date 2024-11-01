package co.edu.escuelaing.microservices.controller;

import co.edu.escuelaing.microservices.exception.TooMuchContentException;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.PostRepository;
import co.edu.escuelaing.microservices.service.PostService;
import co.edu.escuelaing.microservices.service.UserServices;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/users")
public class UserController {

    @Inject
    PostService postService;

    @Inject
    UserServices userServices;

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publicNewPost(Post postRequest) {
        try {

            User user = userServices.getUser(postRequest.getAuthor().getUsername());
            Stream stream = Stream.getInstance();

            Post post = new Post(user, postRequest.getTitle(), postRequest.getContent(), stream);
            postService.save(post);
            return Response.status(Response.Status.CREATED).entity(post).build();
        }catch (TooMuchContentException e){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
