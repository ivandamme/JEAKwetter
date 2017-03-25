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
        ArrayList<User> userss = new ArrayList<>();
        for (User following : user.getFollowing()) {
            userss.add(following);
        }
        for (User users: userss){
            kwetterService.removeFollowing(user,users);
        }
        kwetterService.removeFollowers(user);
        kwetterService.removeUser(user);
    }

    public List<User> getAllUsers() {
        return kwetterService.getUsers();
    }

    public List<Kweet> getAllKweets() {
        return kwetterService.getKweets();
    }
}