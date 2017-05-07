package com.kwetter.rest;

import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;
import com.kwetter.websocket.SessionLister;
//import com.kwetter.websocket.SessionLister;

import javax.ejb.*;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.security.MessageDigest;
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
    @Produces(APPLICATION_JSON)
    @Path("all")
    public Collection<User> findAllUsers(@Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return kweetService.getUsers();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userName}")
    public User findUser(@PathParam("userName") String userName, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return kweetService.findByUserName(userName);
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("id/{id}")
    public User findUserByID(@PathParam("id") int id) {
        return kweetService.findByID(id);
    }


    @GET
    @Path("following/{userName}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowing(@PathParam("userName") String userName, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return kweetService.getFollowing(kweetService.findByUserName(userName));
    }

    @GET
    @Path("followers/{userName}")
    @Produces(APPLICATION_JSON)
    public List<User> getFollowers(@PathParam("userName") String userName, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return kweetService.getFollowers(kweetService.findByUserName(userName));
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



    @POST
    @Path("unFollow")
    @Produces(APPLICATION_JSON)
    public User removeFollower(@FormParam("leader") String usernameUser, @FormParam("following") String following, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        User leader = kweetService.findByUserName(usernameUser);
        User fol = kweetService.findByUserName(following);
        if (leader != null && fol != null) {
            kweetService.removeFollowing(leader, fol);
            return leader;
        }
        return null;
    }

    @POST
    @Path("addFollow")
    @Produces(APPLICATION_JSON)
    public User addFollower(@FormParam("leader") String usernameUser, @FormParam("following") String following, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        User leader = kweetService.findByUserName(usernameUser);
        User fol = kweetService.findByUserName(following);
        if (leader != null && fol != null) {
            kweetService.followUser(leader, fol);

            return leader;
        }
        return null;
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
                    Long upperRange = new Date().getTime();
                    Long lowerRange = 1480000000000L;
                    long randomValue = lowerRange + (long) (r.nextDouble() * (upperRange - lowerRange));
                    Date dt = new Date(randomValue);
                    newKweet.setDate(dt);
                    newUser.addKweet(newKweet);
                }
//                if (newUser.getUserName() == "Niek") {
//                    newUser.setRoles(rolesAdmin);
//                } else {
//                    newUser.setRoles(rolesUser);
//                }
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

    @POST
    @Path("login")
    @Produces(APPLICATION_JSON)
    public User inloggen(@FormParam("userName") String userName, @FormParam("password") String password, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        String hashstring = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            hashstring = hexString.toString();
        } catch (Exception x) {
            System.out.println(x);
        }
        String hashedPassword = (hashstring == null || hashstring.isEmpty()) ? password : hashstring;
        if (kweetService.logIn(userName, hashedPassword)) {
            User kwetteraar = kweetService.findByUserName(userName);
            if (!SessionLister.getInstance().getActiveUsers().contains(userName))
                SessionLister.getInstance().getActiveUsers().add(userName);

            return kwetteraar;
        }
        return null;
    }


    @POST
    @Path("logout")
    @Produces(APPLICATION_JSON)
    public void uitloggen(@FormParam("name") String name, @Context HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = kweetService.findByUserName(name);
        if (user != null && SessionLister.getInstance().getActiveUsers().contains(name)) {
            SessionLister.getInstance().getActiveUsers().remove(name);
        }
    }


    @POST
    @Path("changepic")
    @Produces(APPLICATION_JSON)
    public User wijzigProfielfoto(@FormParam("userName") String userName, @FormParam("picture") String picture, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = kweetService.findByUserName(userName);
        if (user != null) {
            kweetService.changePic(userName, picture);
            return user;
        }
        return null;
    }

    @POST
    @Path("changebio")
    @Produces(APPLICATION_JSON)
    public User wijzigBio(@FormParam("userName") String userName, @FormParam("bio") String bio, @Context HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        User user = kweetService.findByUserName(userName);
        if (user != null) {
            kweetService.changeBio(userName, bio);
            return user;
        }
        return null;
    }


}
