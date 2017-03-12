package com.kwetter.dao;

import com.kwetter.model.User;

import javax.ejb.*;
import javax.naming.InitialContext;
import javax.persistence.*;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */
@LocalBean
@Stateless
public class UserDAO_Impl implements UserDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public UserDAO_Impl() {
    }

    public UserDAO_Impl(EntityManager em) {
        this.em = em;
    }


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
        Query q = em.createNamedQuery("Account.findByUsername");
        q.setParameter("userName", userName);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllFollowing(User user) {
        User findUser = findUserByUserName(user.getUserName());
        return findUser.getFollowing();

//        Query q = em.createQuery("SELECT u FROM User u where u.id in (select f.id from u.following f where u.id =:id)");
//        q.setParameter("id", user.getId());
//        List<User> userlist = q.getResultList();
//        try {
//            return userlist;
//        } catch (NoResultException ex) {
//            return null;
//        }
    }

    @Override
    public int getAmountOfFollowing(User user) {
        User findUser = findUserByUserName(user.getUserName());
        return findUser.getFollowing().size();

//        Query q = em.createQuery("SELECT u from User u inner join u.following f where u.id=:id");
//        q.setParameter("id", user.getId());
//        List<User> userlist = q.getResultList();
//        try {
//            return userlist.size();
//        } catch (NoResultException ex) {
//            return 0;
//        }
    }
}
