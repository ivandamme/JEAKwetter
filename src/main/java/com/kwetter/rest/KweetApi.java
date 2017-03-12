package com.kwetter.rest;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed("admin_role")
    @Path("all")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getAllKweets() {
        return kweetService.getKweets();
    }

    @GET
    @Path("{ID}")
    @Produces(APPLICATION_JSON)
    public Kweet getKweetById(@PathParam("ID") long id) {
        return kweetService.getKweetById(id);
    }


    @GET
    @Path("/user/{id}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getKweetsByUserName(@PathParam("id") String userName) {
        List<Kweet> kweetList = kweetService.findByUserName(userName).getKweets();
        return kweetList;
    }

    @POST
    @Path("/insert")
    @Produces(APPLICATION_JSON)
    public Kweet insertKweet(@FormParam("userName") String userName, @FormParam("text") String text) {
        Kweet kweet = new Kweet(text, kweetService.findByUserName(userName));
        kweetService.placeKweet(kweet);
        return kweet;
    }

    @POST
    @RolesAllowed("admin_role")
    @Path("/delete")
    @Produces(APPLICATION_JSON)
    public Kweet deleteKweet(@FormParam("id") long id) {
        Kweet kweet = kweetService.getKweetById(id);
        kweetService.removeKweet(kweet);
        return kweet;
    }


}
