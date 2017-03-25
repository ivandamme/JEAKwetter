package com.kwetter.beans;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rutger on 06/03/2017.
 */

@RequestScoped
@Named
public class ModeratorBean implements Serializable {

    @EJB
    KweetService kwetterService;

    public void deleteKweet(Kweet kweet) {
        kwetterService.removeKweet(kweet);
    }

    public void deleteUser(User user) {
        ArrayList<User> followings = new ArrayList<>();
        ArrayList<User> followers = new ArrayList<>();
        ArrayList<User> following2 = new ArrayList<>();
        for (User following : user.getFollowing()) {
            followings.add(following);
        }
        for (User follower : user.getFollowers()) {
            followers.add(follower);
        }

        for (User users : followings) {
            kwetterService.removeFollowing(user, users);
        }
        for (User u : followers) {
            for (User u2 : u.getFollowing()) {
                following2.add(u2);
            }
            for (User u3 : following2)
                if (u3.getUserName().equals(user.getUserName())) {
                    kwetterService.removeFollowing(u, u3);
                }
        }
        kwetterService.removeUser(user);
    }

    public List<User> getAllUsers() {
        return kwetterService.getUsers();
    }

    public List<Kweet> getAllKweets() {
        return kwetterService.getKweets();
    }
}