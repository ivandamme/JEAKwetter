package com.kwetter.service;

import com.kwetter.dao.*;
import com.kwetter.model.Kweet;
import com.kwetter.model.Location;
import com.kwetter.model.Role;
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

   // private UserDAO userDAO = new UserDAO_Impl();
    //private KweetDAO kweetDAO = new KweetDAO_Impl();



    //change username
    public void changeUserName(String oldName, String newName) {
        User user = userDAO.findUserByUserName(oldName);
        user.setUserName(newName);
        userDAO.editUser(user);
    }

    //change bio
    public void changeBio(String userName, String bio) {
        User user = userDAO.findUserByUserName(userName);
        user.setBio(bio);
        userDAO.editUser(user);
    }

    //get last 10 kweets
    public List<Kweet> getRecentKweets(String userName) {
        User user = userDAO.findUserByUserName(userName);
        int kweets = user.getKweets().size();
        if (kweets > 10)
            kweets = 10;
        return user.getKweets().subList(0, kweets);
    }

    //Show following
    public List<User> getFollowers(String userName) {
        User user = userDAO.findUserByUserName(userName);
        return user.getFollowing();
    }

    //Place Kweet
    public void placeKweet(String userName, String text) {
        User user = userDAO.findUserByUserName(userName);
        Kweet kweet = new Kweet(text,user);
        kweetDAO.create(kweet);
    }

    //Remove Kweet
    public void removeKweet(long id) {
       kweetDAO.removeKweet(kweetDAO.get(id),kweetDAO.get(id).getPoster());
    }

    //Get all users
    public List<User> getUsers() {
        return userDAO.getAllUsers();
    }

    //Find user by username
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

    //register
    public void register(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userDAO.createUser(user);
    }

    //follow user
    public void followUser(String leaders, String followings) {
        User following = userDAO.findUserByUserName(followings);
        User leader = userDAO.findUserByUserName(leaders);
        leader.addFollowing(following);
        userDAO.editUser(leader);
    }

}
