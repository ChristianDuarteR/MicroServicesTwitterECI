package co.edu.escuelaing.microservices.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@Path("/")
public class GreetingResource {


    @GET
    @Path("/login")
    public Response redirigirAIndex() {
        return Response.status(Response.Status.FOUND)
                       .header("Location", "/auth.html")
                       .build();
    }


    @GET
    @Path("/front/")
    public Response getFront() {
        return Response.status(Response.Status.FOUND)
                       .header("Location", "/front.html" )
                       .build();
        }
}


