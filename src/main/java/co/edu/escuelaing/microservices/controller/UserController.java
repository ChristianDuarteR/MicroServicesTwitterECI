package co.edu.escuelaing.microservices.controller;

import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.service.UserServicesMap;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/v1/users")
public class UserController {
    @Autowired
    UserServicesMap userServices;

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publicNewPost(Post postRequest) {

        /*User user = userServices.getUser(postRequest.getAuthor().getUsername());
            Stream stream = Stream.getInstance();

            Post post = new Post(user, postRequest.getTitle(), postRequest.getContent(), stream);*/
        /*postService.save(post);*/
        return Response.ok().build();
    }
}
