package com.kwetter.dao;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */
@LocalBean
@Stateless
public class KweetDAO_Impl implements KweetDAO {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }
    @Override
    public void create(Kweet k) {
        em.persist(k);
    }

    @Override
    public void edit(Kweet k) {
        em.merge(k);
    }

    @Override
    public void removeKweet(Kweet k,User user) {
        em.remove(k);
    }

    @Override
    public List<Kweet> findAll() {
        return (List<Kweet>) em.createNamedQuery("Kweet.findAll", Kweet.class).getResultList();
    }

    @Override
    public Kweet get(long id) {
        return (Kweet) em.createNamedQuery("Kweet.getById", Kweet.class)
                .setParameter("id", id).getSingleResult();
    }


}
