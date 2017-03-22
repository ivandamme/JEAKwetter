package com.kwetter.beans;

import com.kwetter.model.User;
import com.kwetter.service.KweetService;

import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Niek on 9-3-2017.
 */

@Named(value = "LoginBean")
@RequestScoped
public class LoginBean {

    private String result;
    private User user;
    private String userName;

    @Inject
    HttpServletRequest request;
    @Inject
    HttpSession session;

    private String password;
    private String allUsers = "";

    Long id;
    @Inject
    KweetService kwetterService;

    public User getUser() {
        return user;
    }

    public String getResult() {
        return result;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }


    public void CheckValidUser() throws IOException {
        User user = kwetterService.findByUserName(userName);

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(user.getUserName(), password);
            if (request.isUserInRole("user_role")) {
                result = "you are a user";
                goToMainPageByUserID(user.getId());
            } else {
                result = "you are a nothing";
                goToMainPageByUserID(user.getId());
            }


        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToMainPageByUserID(Long id) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml?id=" + id);

    }

    public List<User> getAllUsers() {
        List<User> userlist;
        userlist = kwetterService.getUsers();
        return userlist;

    }

    public void signOut() {
        try {
            request.logout();
            session.invalidate();
            System.out.println("Signout invoked");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
                    (FacesContext.getCurrentInstance(), null, "/index.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error Signout -" + ex.getMessage());
        }
    }


}