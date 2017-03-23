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
import java.util.List;

/**
 * Created by Rutger on 06/03/2017.
 */

@RequestScoped
@Named
public class ModeratorBean implements Serializable {

    @EJB
    KweetService kwetterService;


    public List<User> getAllUsers() {
        return kwetterService.getUsers();
    }

    public List<Kweet> getAllKweets() {
        return kwetterService.getKweets();
    }
}