package co.edu.escuelaing.microservices.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class GreetingResource {

 

    @GET
    public Response redirigirAIndex() {
        return Response.status(Response.Status.FOUND)
                       .header("Location", "/index.html")
                       .build();
    }
    @GET
@Path("/prueba/")
public Response prueba(@PathParam("user") String user) {
    return Response.status(Response.Status.FOUND)
                   .header("Location", "/front.html" )
                   .build();
}



}
