package com.kwetter.controller;

import com.kwetter.dao.LocationDAO_Impl;
import com.kwetter.dao.UserDAO_Impl;
import com.kwetter.model.Location;
import com.kwetter.model.User;

import javax.annotation.ManagedBean;
import javax.enterprise.context.*;
import javax.inject.*;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */
@ManagedBean
@ApplicationScoped
@Named
public class testcontroller {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    @SessionScoped
    LocationDAO_Impl locationDAOimpl ;

    public List<User> getUsers() {
        Location location = new Location(1, 1, "Venlo");


        User userToAdd = new User(
                "Rutger",
                "I'm the developer",
                "Eindhoven",
                location,
                "https://pyld.io"
        );


        locationDAOimpl.createLocation(location);


        TypedQuery<User> query = entityManager.createNamedQuery("Account.findAll", User.class);
        return query.getResultList();
    }
}

