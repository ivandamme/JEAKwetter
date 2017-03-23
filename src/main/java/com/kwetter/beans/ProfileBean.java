package com.kwetter.beans;

import com.kwetter.model.*;
import com.kwetter.service.KweetService;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by Niek on 16-3-2017.
 */

@ManagedBean(name = "profileBean", eager = true)
public class ProfileBean {

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    @Inject
    KweetService kwetterService;
    private User user;
    private String newKweetContent;

    public ProfileBean() {
        // Empty constructor for dependency injection purposes.
    }


    @Inject
    public void setKweetService(KweetService ks) {
        kwetterService = ks;
    }


    public User getUser() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        user = this.kwetterService.findByUserName(username);
        return user;

    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewKweetContent() {
        return newKweetContent;
    }

    public void setNewKweetContent(String newKweetContent) {
        this.newKweetContent = newKweetContent;
    }

    public Kweet getLastKweet() {
        List<Kweet> kweets = user.getKweets();
        Collections.sort(kweets);
        return kweets.get(kweets.size() - 1);
    }

    public void sendKweet() {
        Kweet kweet = new Kweet(newKweetContent, user);
        kwetterService.placeKweet(kweet);
        setNewKweetContent(null);
    }

    public List<Kweet> getTimeline() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        user = this.kwetterService.findByUserName(username);
        List<Kweet> kweets = kwetterService.getOwnAndFollowing(user);
        return kweets;
    }

    public List<Kweet> getAllKweetsUser() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        user = this.kwetterService.findByUserName(username);
        List<Kweet> kweets = kwetterService.findByUserName(user.getUserName()).getKweets();
        Collections.sort(kweets);
        Collections.reverse(kweets);
        return kweets;
    }

    public void placeKweet() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        user = this.kwetterService.findByUserName(username);
        Kweet kweet = new Kweet(newKweetContent, user);
        kwetterService.placeKweet(kweet);
        setNewKweetContent(null);
    }



}
