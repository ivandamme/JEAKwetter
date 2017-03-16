package com.kwetter.controller;

import com.kwetter.model.*;
import com.kwetter.service.KweetService;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Niek on 16-3-2017.
 */

@ManagedBean
public class profilecontroller {

    @Inject
    private KweetService ks;

    private String profielFoto;
    private String userName;
    private String bio;
    private List<Kweet> kweets;
    private List<User> following;

    public profilecontroller() {
        try {
            setUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            User User = ks.findByUserName(getUsername());
            setBio(User.getBio());
            setKweets(User.getKweets());
            setFollowing(User.getFollowing());
        }
        catch (Exception x) {
            System.out.println(x);
        }
    }

    public String getProfielFoto() {
        return profielFoto;
    }

    public void setProfielFoto(String profielFoto) {
        this.profielFoto = profielFoto;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    
}
