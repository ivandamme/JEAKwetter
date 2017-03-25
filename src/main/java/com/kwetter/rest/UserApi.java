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
import java.util.*;

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
        User user = new User(userName, password);
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
                kweetService.followUser(userLeader, userFollowing);
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
                kweetService.removeFollowing(userLeader, userFollower);
            }
        }
        return kweetService.getFollowing(userLeader);
    }

    @GET
    @Path("insert")
    public String initUsers() {
        Role roleAdmin = new Role("admin");
        Role roleUser = new Role("user");

        Collection<Role> rolesAdmin = new ArrayList<Role>();
        rolesAdmin.add(roleAdmin);

        Collection<Role> rolesUser = new ArrayList<Role>();
        rolesUser.add(roleUser);

        ArrayList<String> randomLocations = new ArrayList<String>() {{
            add("Venlo");
            add("Eindhoven");
            add("Amsterdam");
            add("New York");
            add("Tilburg");
            add("Den Bosch");
            add("Tegelen");
            add("Groningen");
            add("Utrecht");
            add("Alkmaar");
        }};

        ArrayList<String> randomNames = new ArrayList<String>() {{
            add("Niek");
            add("Henk");
            add("Pieter");
            add("Joseph");
            add("Ali");
            add("Achmed");
            add("Geert");
            add("Killer Kamal");
            add("Jeffrey");
            add("Jan");
        }};

        ArrayList<String> randomPictures = new ArrayList<String>() {{
            add("https://pbs.twimg.com/profile_images/822132298556571648/pmTDnMBX_400x400.jpg");
            add("https://alphenaandenrijn.pvda.nl/wp-content/blogs.dir/370/pvda_files/cache/th_84d3103e6b09f4801b9f6d2d299003c2_1378324901HenkGoes.jpg");
            add("https://hekwerk.nl/media/images/artists/thumbnails/pieterderks-pas-640x_.jpg");
            add("https://pbs.twimg.com/profile_images/695757811725307905/5evAteta.jpg");
            add("https://pbs.twimg.com/profile_images/801042444599037953/bo5Puy3P.jpg");
            add("https://pbs.twimg.com/profile_images/483722140907102209/z3-relS4.jpeg");
            add("https://pbs.twimg.com/profile_images/1813017013/PenB_portretten_ZK_0001_PenB_portret_02.jpg");
            add("https://pbs.twimg.com/profile_images/826811657364074496/ydoY25sP.jpg");
            add("https://pbs.twimg.com/profile_images/2244168741/K01rhMMj_400x400");
            add("https://pbs.twimg.com/profile_images/430470855777591296/dHWqRB4X.jpeg");
        }};

        Location location = new Location(51, 6, "Venlo");
        for (String locationName : randomLocations) {
            Location newLocation = new Location(
                    55,
                    15,
                    locationName
            );

            for (String name : randomNames) {
                User newUser = new User(name, "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                        "Hi! I'm " + name + " and I absolutely love Maatwerk and to slack everyday!", newLocation,
                        "https://fontys.nl/", randomPictures.get(new Random().nextInt(9)));

                for (int i = 0; i < (new Random()).nextInt(10 - 1) + 1; i++) {
                    Kweet newKweet = new Kweet(
                            name + "'s " + (i + 1) + " very special kweet",
                            newUser
                    );
                    Random r = new Random();
                    Long upperRange =new Date().getTime();
                    Long lowerRange =1480000000000L;
                    long randomValue = lowerRange + (long)(r.nextDouble()*(upperRange - lowerRange));
                    Date dt =new Date(randomValue);
                    newKweet.setDate(dt);
                    newUser.addKweet(newKweet);
                }
                if (newUser.getUserName() == "Niek") {
                    newUser.setRoles(rolesAdmin);
                } else {
                    newUser.setRoles(rolesUser);
                }
                kweetService.createUser(newUser);
            }
        }

        kweetService.findByUserName("Niek").addFollowing(kweetService.findByUserName("Pieter"));
        kweetService.findByUserName("Henk").addFollowing(kweetService.findByUserName("Niek"));

        kweetService.editUser(kweetService.findByUserName("Niek"));
        kweetService.editUser(kweetService.findByUserName("Pieter"));
        kweetService.editUser(kweetService.findByUserName("Henk"));


        return "Success";

    }


}
