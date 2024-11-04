package co.edu.escuelaing.microservices.controller;

import co.edu.escuelaing.microservices.exception.SecurityException;
import co.edu.escuelaing.microservices.model.Post;
import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.service.UserServicesMap;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.springframework.http.ResponseEntity;

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
    @RolesAllowed({ "User", "Admin" })
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
    @PermitAll
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
                        + " hasJWT: %s",
                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}
