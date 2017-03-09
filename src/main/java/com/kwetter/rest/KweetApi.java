package com.kwetter.rest;

import com.kwetter.model.Kweet;
import com.kwetter.service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Niek on 8-3-2017.
 */

@Stateless
@Path("/kweets")
public class KweetApi {

    //
    @Inject
    KweetService kweetService;


    @GET
    @Path("all")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getAllKweets() { return kweetService.getKweets(); }

    @GET
    @Path("{ID}")
    @Produces(APPLICATION_JSON)
    public Kweet getKweetById(@PathParam("ID") long id) {
        return kweetService.getKweetById(id);
    }

}
