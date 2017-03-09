package com.kwetter.service;

import com.kwetter.dao.*;
import com.kwetter.model.Kweet;

import com.kwetter.model.User;

import javax.ejb.*;
import javax.inject.*;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */

@Stateless
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
     * Creates a new user in the kwetter application
     *
     * @param user the new user
     */
    public void createUser(User user) {
        userDAO.createUser(user);
    }

    /**
     * Updates user information in the kwetter application.
     * @param user User to be updated
     */
    public void editUser(User user) {
        userDAO.editUser(user);
    }

    //Show following
    public List<User> getFollowing(User user) {
        return userDAO.getAllFollowing(user);
    }

    //Place Kweet
    public void placeKweet(Kweet kweet) {
        kweetDAO.create(kweet);
    }

    //Remove Kweet
    public void removeKweet(long id) {
        kweetDAO.removeKweet(kweetDAO.get(id), kweetDAO.get(id).getPoster());
    }

    //Get all Kweets
    public List<Kweet> getKweets() {
        return kweetDAO.findAll();
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

    //Change users role
    public void changeRole(String userName, long rolId) {
        throw new UnsupportedOperationException("Not supported yet.");
       /* Role role = rolDao.get(rolId);
        User user = userDAO.findUserByUserName(userName);
        user.setRole(role);
        userDAO.editUser(user);
        */
    }

    public boolean logIn(String userName, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void logOut() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Follows a user
     *
     * @param leaders    the person following someone
     * @param followings the person being followed
     */
    public void followUser(User leader, User following) {
        leader.addFollowing(following);
        userDAO.editUser(leader);
    }

}
