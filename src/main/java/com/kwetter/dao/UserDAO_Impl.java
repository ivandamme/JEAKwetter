package com.kwetter.dao;

import com.kwetter.model.User;

import javax.ejb.*;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */
@LocalBean
@Stateless
public class UserDAO_Impl implements UserDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em ;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public void editUser(User user) {
        em.merge(user);
    }

    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = em.createNamedQuery("Account.findAll");
        return (List<User>) query.getResultList();
    }

    @Override
    public User findUserByUserName(String userName) {
        User u = em.createNamedQuery("Account.findByUsername", User.class)
                .setParameter("userName", userName).getSingleResult();
        return u;
    }

    @Override
    public List<User> getAllFollowing(User user) {
        return user.getFollowing();
    }

    @Override
    public int getAmountOfFollowing(User user) {
        return user.getFollowing().size();
    }
}
