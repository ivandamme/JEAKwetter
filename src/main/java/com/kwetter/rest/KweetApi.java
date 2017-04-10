package com.kwetter.rest;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Niek on 8-3-2017.
 */

@ApplicationScoped
@Stateless
@Path("/kweets")
public class KweetApi {

    //
    @Inject
    KweetService kweetService;


    @GET
    @Path("all")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getAllKweets(@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
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
    public Kweet insertKweet(@FormParam("userName") String userName, @FormParam("text") String text,@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        Kweet kweet = new Kweet(text, kweetService.findByUserName(userName));
        kweetService.placeKweet(kweet);
        return kweet;
    }

    @POST
    @Path("/delete")
    @Produces(APPLICATION_JSON)
    public List<Kweet> deleteKweet(@FormParam("id") long id,@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        User user = kweetService.getKweetById(id).getPoster();
        kweetService.removeKweet(kweetService.getKweetById(id));
        return kweetService.findByUserName(user.getUserName()).getKweets();
    }

    @POST
    @Path("/deleteForAll")
    @Produces(APPLICATION_JSON)
    public List<Kweet> deleteKweetForAll(@FormParam("id") long id,@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        kweetService.removeKweet(kweetService.getKweetById(id));
        return kweetService.getKweets();
    }

    @GET
    @Path("timeline/{userName}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getTimeline(@PathParam("userName") String userName, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetService.getOwnAndFollowing(kweetService.findByUserName(userName));
    }

    @GET
    @Path("content/{content}")
    @Produces(APPLICATION_JSON)
    public List<Kweet> getKweetsByContent(@PathParam("content") String content, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin" , "*");
        return kweetService.getKweetByText(content);
    }
}
