//package co.edu.escuelaing.microservices.controller;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

<<<<<<< HEAD
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
=======
//@Path("/")
//public class GreetingResource {
//
//    @GET
//    public Response redirigirAIndex() {
//        return Response.status(Response.Status.FOUND)
//                       .header("Location", "/auth.html")
//                       .build();
//    }
//}
>>>>>>> 3c03c88058550cb2599bd13abd288a67422beb83
