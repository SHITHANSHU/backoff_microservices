package io.dropwizard.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/service-walrus")
//@Produces(MediaType.APPLICATION_JSON)
public interface serviceRougeResource {

//    http://localhost:8085/service-walrus/hello
    @GET
    @Path("/hello")
    public Response getHelloWorld();



}
