package com.kwetter.rest;

import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
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

    @GET
    @Path("insert")
    public void initUsers() {
        Location location = new Location(1, 1, "Venlo");
        Role roleAdmin = new Role("admin");
        Role roleUser = new Role("user");

        Collection<Role> rolesAdmin = new ArrayList<Role>();
        rolesAdmin.add(roleAdmin);

        Collection<Role> rolesUser = new ArrayList<Role>();
        rolesUser.add(roleUser);

        User userToAdd = new User(
                "Niek",
                "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "Head of the Maatwerk division (MWD) . Responsible for signing Maatwerk contracts and making Le Epic Maatwerk Memes. ",
                location,
                "https://pyld.io",
                "https://pbs.twimg.com/profile_images/822132298556571648/pmTDnMBX_400x400.jpg"
        );


        User userToAdd2 = new User(
                "Henk",
                "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "All aboard the maatwerk train",
                location,
                "https://pyld.io",
                "https://alphenaandenrijn.pvda.nl/wp-content/blogs.dir/370/pvda_files/cache/th_84d3103e6b09f4801b9f6d2d299003c2_1378324901HenkGoes.jpg"
        );

        User niekFollowing = new User(
                "Pieter",
                "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "HILDA",
                location,
                "NEE IS NEE HE",
                "https://hekwerk.nl/media/images/artists/thumbnails/pieterderks-pas-640x_.jpg"
        );
        userToAdd.setRoles(rolesAdmin);
        userToAdd2.setRoles(rolesUser);
        niekFollowing.setRoles(rolesUser);


        Kweet kweetToAdd1 = new Kweet(
                "Wat een kansloze les is dit weer zeg #Fontys",
                userToAdd
        );

        Kweet kweetToAdd2 = new Kweet(
                "2 Memes a day keeps the Maatwerk away #HijdoetHetNiet",
                userToAdd
        );

        Kweet kweetToAdd3 = new Kweet(
                "My neck My neck My pussy and My #crack",
                userToAdd2
        );

        userToAdd.addKweet(kweetToAdd1);
        userToAdd.addKweet(kweetToAdd2);
        userToAdd2.addKweet(kweetToAdd3);

        kweetService.createUser(userToAdd);
        kweetService.createUser(niekFollowing);

        userToAdd.addFollowing(niekFollowing);

        kweetService.editUser(userToAdd);
        kweetService.editUser(niekFollowing);



          kweetService.createUser(userToAdd2);
    }


}
