package com.kwetter.dao;

import com.kwetter.model.Kweet;
import com.kwetter.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */
public class KweetDAOCollection_Impl implements KweetDAO  {

    private List<Kweet> kweets = new ArrayList<>();

    public KweetDAOCollection_Impl() {
    }

    @Override
    public void create(Kweet kweet) {
        kweets.add(kweet);
    }

    @Override
    public void edit(Kweet k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeKweet(Kweet kweet) {
        kweets.remove(kweet);
    }

    @Override
    public List<Kweet> findAll() {
        return kweets;
    }

    @Override
    public Kweet get(long id) {
        for (Kweet k: kweets) {
            if(k.getId()==id){
                return k;
            }
        }
        return null;
    }


}
