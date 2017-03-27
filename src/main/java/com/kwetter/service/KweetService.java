package com.kwetter.service;

import com.kwetter.dao.*;
import com.kwetter.model.Kweet;

import com.kwetter.model.User;

import javax.ejb.*;
import javax.faces.bean.ApplicationScoped;
import javax.inject.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */

@Stateless
@ApplicationScoped
@Named
public class KweetService {


    @Inject
    private UserDAO_Impl userDAO;

    @Inject
    private KweetDAO_Impl kweetDAO;
/*
     private UserDAO userDAO = new UserDAOCollection_Impl();
    private KweetDAO kweetDAO = new KweetDAOCollection_Impl();

*/


    /**
     * Used for mockito testing only!
     *
     * @param dao dao to set
     */
    public void setKwetterDAO(KweetDAO_Impl dao) {
        kweetDAO = dao;
    }

    /**
     * Used for mockito testing only!
     *
     * @param dao dao to set
     */
    public void setUserDAO(UserDAO_Impl dao) {
        userDAO = dao;
    }

    /**
     * Used for mockito testing only!
     *
     * @param dao dao to set
     */
    public void setKweetDAO(KweetDAO_Impl dao) {
        kweetDAO = dao;
    }

    /**
     * Creates a new user in the kwetter application
     *
     * @param user the new user
     */
    public void createUser(User user) {
        Boolean exists = false;
        for (User u : userDAO.getAllUsers()) {
            if (u.getUserName().equals(user.getUserName())) {
                exists = true;
            }
        }
        if (!exists) userDAO.createUser(user);

    }

    /**
     * Updates user information in the kwetter application.
     *
     * @param user User to be updated
     */
    public void editUser(User user) {
        userDAO.editUser(user);
    }

    /**
     * Updates kweet information in the kwetter application.
     *
     * @param kweet Kweet to be updated
     */
    public void editKweet(Kweet kweet) {
        kweetDAO.edit(kweet);
    }

    //Show following
    public List<User> getFollowing(User user) {
        return userDAO.getAllFollowing(user);
    }

    //Place Kweet
    public void placeKweet(Kweet kweet) {
        User user = kweet.getPoster();
        user.addKweet(kweet);
        userDAO.editUser(user);
        kweetDAO.create(kweet);
    }

    //Remove Kweet
    public void removeKweet(Kweet kweet) {
        User user = kweet.getPoster();
        user.removeKweet(kweet);
        userDAO.editUser(user);
        kweetDAO.removeKweet(kweet);
    }

    //Remove User
    public void removeUser(User user) {
        userDAO.removeUser(user);
    }

    //Get all Kweets
    public List<Kweet> getKweets() {
        List<Kweet> kweets = kweetDAO.findAll();
        Collections.sort(kweets);
        Collections.reverse(kweets);
        return kweets;
    }

    //Find Kweet by ID
    public Kweet getKweetById(long id) {
        return kweetDAO.get(id);
    }

    //Get all users
    public List<User> getUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Returns a user with the given username
     *
     * @param userName the username
     * @return a user object
     */
    public User findByUserName(String userName) {
        return userDAO.findUserByUserName(userName);
    }

    /**
     * Follows a user
     *
     * @param leader    the person following someone
     * @param following the person being followed
     */
    public void followUser(User leader, User following) {
        leader.addFollowing(following);
        userDAO.editUser(leader);
        userDAO.editUser(following);
    }

    public void removeFollowing(User leader, User following) {
        leader.removeFollowing(following);
        userDAO.editUser(leader);
        userDAO.editUser(following);
    }

    public void removeFollower(User leader, User follower) {
        leader.removeFollower(follower);
        userDAO.editUser(leader);
        userDAO.editUser(follower);
    }


    public List<Kweet> getOwnAndFollowing(User user) {
        List<Kweet> kweets = new ArrayList<>();

        for (User u : userDAO.findUserByUserName(user.getUserName()).getFollowing()) {
            kweets.addAll(u.getKweets());
        }
        kweets.addAll(userDAO.findUserByUserName(user.getUserName()).getKweets());
        int count = kweets.size();
        if (count > 50)
            count = 50;
        Collections.sort(kweets);
        Collections.reverse(kweets);
        return kweets.subList(0, count);

    }

    public List<Kweet> getKweetByText(String text) {
        List<Kweet> sortedKweets = kweetDAO.findByText(text);
        Collections.sort(sortedKweets);
        Collections.reverse(sortedKweets);
        return sortedKweets;
    }
}
