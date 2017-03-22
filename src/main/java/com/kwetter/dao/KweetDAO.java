package com.kwetter.dao;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;

import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */
public interface KweetDAO {
    /**
     * Creates a new Kweet object

     */
    void create(Kweet k);


    /**
     * Edits a Kweet
     * @param k
     */
    void edit( Kweet k);


    /**
     * Removes a Kweet object
     *
     * @param k
     */
    void removeKweet(Kweet k);


    /**
     * Returns all kweets in this application
     * @return list of kweets
     */
    List<Kweet> findAll() ;

    /**
     * Returns a kweet based on id
     * @return kweet
     */
    Kweet get(long id);


}
