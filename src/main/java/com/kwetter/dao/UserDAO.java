package com.kwetter.dao;
import com.kwetter.model.User;

import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */
public interface UserDAO {

    /**
     * Creates a new User object
     *
     * @param user
     */
    void createUser(User user);

    /**
     * Edits a User object
     *
     * @param user
     */
    void editUser(User user);

    /**
     * Removes a User object
     *
     * @param user
     */
    void removeUser(User user);

    /**
     * Retrieves all users from the kwetter application
     *
     * @return
     */
    List<User> getAllUsers();


    /**
     * Return user object with given username
     *
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * Retrieves all the users that the given username follows!
     * @param user
     * @return
     */
    List<User> getAllFollowing(User user);

    /**
     * Returns the number of users the given user follows
     * @param user
     * @return
     */
    int getAmountOfFollowing(User user);



}
