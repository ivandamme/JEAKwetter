package com.kwetter.beans;

import com.kwetter.model.*;
import com.kwetter.service.KweetService;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
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

@RequestScoped
@ManagedBean(name = "profileBean")
public class ProfileBean {

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    @EJB
    KweetService kwetterService;
    private String username;
    private User user;
    private String newKweetContent;

    public ProfileBean() {
        // Empty constructor for dependency injection purposes.
    }

    @PostConstruct
    public void postContructor() {
        try {
            user = kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        }
        catch (Exception x) {
           //TODO
        }
    }

    public boolean isValid() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        boolean isValid = this.kwetterService.findByUserName(username) != null;
        if (isValid) {
            this.username = username;
        }
        return isValid;
    }

    public boolean isOwnProfile() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        User test = kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
       return username.equals(test.getUserName());
    }

    public boolean alreadyFollow(){
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        User test = kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        for (User u: test.getFollowing()) {
            if(u.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        user = kwetterService.findByUserName(username);
        return user;
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
        List<Kweet> kweets = kwetterService.getOwnAndFollowing(kwetterService.findByUserName(username));
        return kweets;
    }

    public void placeKweet() {
        Kweet kweet = new Kweet(newKweetContent, user);
        kwetterService.placeKweet(kweet);
        setNewKweetContent(null);
    }

    public void followUser()
    {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        User following = kwetterService.findByUserName(username);
        User leader = kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        kwetterService.followUser(leader,following);
    }

    public void unFollowUser()
    {
        Map<String, String> parameterMap = this.externalContext.getRequestParameterMap();
        String username = parameterMap.get("u");
        User following = kwetterService.findByUserName(username);
        User leader = kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        kwetterService.removeFollower(leader,following);
    }


}
