package com.fluig.identity.swagger.api.rest;

import com.fluig.identity.swagger.service.model.ApiDTO;
import com.fluig.identity.swagger.service.model.ApiService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/apis")
public class ApiResource {

    @Inject
    private ApiService apiService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") @NotNull Integer id){
        apiService.findById(id);
        return Response.ok().build();
    }

    @GET
    public Response findAll(){
        return Response.ok(apiService.findAll()).build();
    }
}
