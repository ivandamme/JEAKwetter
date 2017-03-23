package com.kwetter.beans;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Niek on 23-3-2017.
 */

@RequestScoped
@Named
public class SearchBean {

    @Inject
    KweetService kwetterService;
    private List<Kweet> kweets;
    private List<User> users;

    private String text;

    public SearchBean() {
        // Empty constructor for dependency injection purposes.
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setKweetsByText() {
        kweets = kwetterService.getKweetByText(text);
    }

}
