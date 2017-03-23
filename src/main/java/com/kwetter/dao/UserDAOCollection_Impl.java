package com.kwetter.dao;

import com.kwetter.model.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */
@Local(UserDAO.class)
@Stateless
public class UserDAOCollection_Impl implements UserDAO  {

    private List<User> userList = new ArrayList<>();


    public UserDAOCollection_Impl() {
}


    @Override
    public void createUser(User user) {
        userList.add(user);
    }

    @Override
    public void editUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeUser(User user) {
        userList.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User findUserByUserName(String userName) {
        for (User u : userList) {
            if (u.getUserName().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllFollowing(User user) {
        return user.getFollowing();
    }

    @Override
    public int getAmountOfFollowing(User user) {
        return user.getFollowing().size();
    }

    @Override
    public List<User> getAllFollowers(User user) {
        return null;
    }

    @Override
    public List<User> findUserByUserNameContains(String partOfUsername) {
        return null;
    }
}
