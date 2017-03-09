package com.kwetter.rest;

import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.ejb.*;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Created by Niek on 7-3-2017.
 */

@Stateless
@Path("/users")
public class UserApi {

    @Inject
    KweetService kweetService;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("all")
    public Collection<User> findAllUsers() {
        return kweetService.getUsers();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userName}")
    public User findUser(@PathParam("userName") String userName) {
        return kweetService.findByUserName(userName);
    }

    @GET
    @Path("following/{userName}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowing(@PathParam("userName") String userName) {
        return kweetService.getFollowing(userName);
    }

    @GET
    @Produces(TEXT_PLAIN)
    @Path("count")
    public int count() {
        return kweetService.getUsers().size();
    }




}
