package com.kwetter.controller;

import com.kwetter.dao.KweetDAO_Impl;
import com.kwetter.dao.LocationDAO_Impl;
import com.kwetter.dao.UserDAO_Impl;
import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.annotation.ManagedBean;
import javax.enterprise.context.*;
import javax.inject.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */
@ManagedBean
@ApplicationScoped
@Named
public class testcontroller {


    @Inject
    KweetService ks;

    @Inject
    @SessionScoped
    UserDAO_Impl UDC;

    @Inject
    @SessionScoped
    KweetDAO_Impl KDC;



    public List<User> getUsers() {

        makeSomeUsers();
        return UDC.getAllUsers();

    }

    public void makeSomeUsers()
    {
        Location location = new Location(1, 1, "Venlo");
        Role role = new Role("Moderater");

        User userToAdd = new User(
                "Niek",
                "gekkePassword",
                "This is my Bio",
                location,
                "https://pyld.io",
                role
        );

        User userToAdd2 = new User(
                "NiekVolger",
                "MaatwerkBoys",
                "All aboard the maatwerk train",
                location,
                "https://pyld.io",
                role
        );

        User niekFollowing = new User(
                "NiekVolgen",
                "DIKKE BMW",
                "HILDA",
                location,
                "NEE IS NEE HE",
                role
        );




        Kweet kweetToAdd1 = new Kweet(
                "Kweet 1",
                userToAdd
        );

        Kweet kweetToAdd2 = new Kweet(
                "Kweet 2",
                userToAdd
        );

        Kweet kweetToAdd3 = new Kweet(
                "Kweet 3",
                userToAdd2
        );

        userToAdd.addKweet(kweetToAdd1);
        userToAdd.addKweet(kweetToAdd2);
        userToAdd2.addKweet(kweetToAdd3);
        userToAdd.addFollowing(niekFollowing);

        ks.createUser(userToAdd);
        ks.createUser(userToAdd2);
    }
}

