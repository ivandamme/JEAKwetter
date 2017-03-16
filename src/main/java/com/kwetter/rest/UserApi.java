package com.kwetter.rest;

import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Created by Niek on 7-3-2017.
 */

@ApplicationScoped
@Stateless
@Path("/users")
public class UserApi {

    @Inject
    KweetService kweetService;

    @GET
    @RolesAllowed("admin")
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
        return kweetService.getFollowing(kweetService.findByUserName(userName));
    }

    @GET
    @Produces(TEXT_PLAIN)
    @Path("count")
    public int count() {
        return kweetService.getUsers().size();
    }


    @POST
    @Path("/remove")
    @Produces(APPLICATION_JSON)
    public String removeUser(@FormParam("userName") String userName) {
        User user = kweetService.findByUserName(userName);
        kweetService.removeUser(user);
        return "User has been removed.";
    }

    @POST
    @Path("/create")
    @Produces(APPLICATION_JSON)
    public String createUser(@FormParam("userName") String userName, @FormParam("password") String password) {
        User user = new User(userName,password);
        kweetService.createUser(user);
        User addedUser = kweetService.findByUserName(userName);
        if (addedUser != null) {
            return user.getUserName() + " successfully added.";
        }
        return "User could not be added.";
    }

    @GET
    @Path("addFollow/{leader}/{following}")
    @Produces(APPLICATION_JSON)
    public Collection<User> addFollower(@PathParam("leader") String usernameUser, @PathParam("following") String usernameFollow) {
        User userLeader = kweetService.findByUserName(usernameUser);
        User userFollowing = kweetService.findByUserName(usernameFollow);
        if (userLeader.getUserName() != null || userFollowing.getUserName() != null) {
            if (!userLeader.getFollowing().contains(userFollowing)) {
                kweetService.followUser(userLeader,userFollowing);
            }
        }
        return kweetService.getFollowing(userLeader);
    }

    @GET
    @Path("unFollow/{leader}/{following}")
    @Produces(APPLICATION_JSON)
    public Collection<User> removeFollower(@PathParam("leader") String usernameUser, @PathParam("following") String usernameFollow) {
        User userLeader = kweetService.findByUserName(usernameUser);
        User userFollower = kweetService.findByUserName(usernameFollow);
        if (userLeader.getUserName() != null || userFollower.getUserName() != null) {
            if (userLeader.getFollowing().contains(userFollower)) {
                kweetService.removeFollower(userLeader, userFollower);
            }
        }
        return kweetService.getFollowing(userLeader);
    }



}
