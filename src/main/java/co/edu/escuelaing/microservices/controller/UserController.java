package co.edu.escuelaing.microservices.controller;

import co.edu.escuelaing.microservices.dto.PostDTO;
import co.edu.escuelaing.microservices.dto.UserDTO;
import co.edu.escuelaing.microservices.exception.SecurityException;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.Stream;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.service.UserServicesMap;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;


import java.util.List;

@Path("/api/v1/users")
@RequestScoped
public class UserController {

    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim(standard = Claims.email)
    String email;

    @Inject
    UserServicesMap userServices;

    @GET
    @Path("/permit-all")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        return userServices.getUsers();
    }


    @GET
    @Path("/")
    @RolesAllowed({ "user", "admin" })
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserInfo(@Context SecurityContext ctx) throws SecurityException {
        System.out.println(getResponseString(ctx));
        if (email == null){
            throw new SecurityException("User anonymous");
        }

        User user = userServices.getUser(email);
        if (user == null){
            throw new SecurityException("User " + email + " not registered");
        }

        return user;
    }

    @POST
    @Path("/register")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getFullName() == null || userDTO.getUserName() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email, FullName, and username are required.")
                    .build();
        }
        if (userServices.getUser(userDTO.getEmail()) != null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User already registered")
                    .build();
        }
        // Guardar usuario usando el servicio
        try {
            User newUser = userServices.saveUser(userDTO.getEmail(),userDTO.getUserName(),userDTO.getFullName());
            return Response.status(Response.Status.CREATED) // 201 Created
                    .entity(newUser)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error registering the user.")
                    .build();
        }
    }
 

    @POST
    @Path("/newPost")
    @RolesAllowed({ "user", "admin" })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStream(@Context SecurityContext ctx, PostDTO postDTO){
        System.out.println(getResponseString(ctx));
        try {
            Stream newStream = userServices.newStream(email);
            Post newPost = userServices.newPost(email, postDTO);
            userServices.newPostToStream(email, newStream.getStreamId(), newPost);
            return Response.status(Response.Status.CREATED)
                    .entity(newPost)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating the stream.")
                    .build();
        }
    }


    @POST
    @Path("/newPostToStream/{idStream}")
    @RolesAllowed({ "user", "admin" })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubStream(@Context SecurityContext ctx,
                                    @PathParam("idStream") String idStream,
                                    PostDTO postDTO){
        System.out.println(getResponseString(ctx));
        try {
            Stream newStream = userServices.getStreamId(idStream, email);
            Post newPost = userServices.newPost(email, postDTO);
            userServices.newPostToStream(email, newStream.getStreamId(), newPost);
            return Response.status(Response.Status.CREATED)
                    .entity(newStream)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating the stream.")
                    .build();
        }
    }


    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }
        return String.format("hello %s,"
                        + " isHttps: %s,"
                        + " authScheme: %s,"
                        + " hasJWT: %s"
                        + " email: %s",
                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt(),email);
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}
