package com.kwetter.beans;

/**
 * Created by Niek on 23-3-2017.
 */

import com.kwetter.model.Role;
import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by Niek on 23-3-2017.
 */
@RequestScoped
@Named
public class LoggedUserBean implements Serializable {

    @Inject
    HttpServletRequest request;
    @Inject
    HttpSession session;

    @EJB
    KweetService kwetterService;

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    public User getUser() {
        return kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
    }

    public boolean getLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null;
    }

    public String signOut() {
        try {
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index?faces-redirect=true";
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error Signout -" + ex.getMessage());
            return null;
        }
    }

    public boolean isAdmin() {
        User user = kwetterService.findByUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        if (user != null) {
            for (Role r : user.getRoles())
                if (r.getRoleName().equals("admin")) {
                    return true;
                }
        }
        return false;
    }

}

