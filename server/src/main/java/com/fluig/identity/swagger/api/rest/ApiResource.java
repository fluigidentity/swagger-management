package com.fluig.identity.swagger.api.rest;

import com.fluig.identity.swagger.service.model.ApiDTO;
import com.fluig.identity.swagger.service.model.ApiService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@ApplicationScoped
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
}
