package com.kwetter.dao;

import com.kwetter.model.Location;
import com.kwetter.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */
@LocalBean
@Stateless
public class LocationDAO_Impl implements LocationDAO {


    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em ;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createLocation(Location location) {
        em.persist(location);
    }

    @Override
    public void editLocation(Location location) {
        em.merge(location);
    }

    @Override
    public void removeLocation(Location location) {
        em.remove(location);
    }

    @Override
    public List<Location> getAll() {
        Query query = em.createNamedQuery("Location.findAll");
        return (List<Location>) query.getResultList();
    }

    @Override
    public List<Location> getByName(String name) {
        Query query  = em.createNamedQuery("Location.findByName");
        return (List<Location>) query.getResultList();
    }


}
