package com.kwetter.dao;

import com.kwetter.model.Kweet;
import com.kwetter.model.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 8-3-2017.
 */
public class LocationDAOCollection_Impl implements LocationDAO {

    private List<Location> locations = new ArrayList<>();

    @Override
    public void createLocation(Location location) {
        locations.add(location);
    }

    @Override
    public void editLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeLocation(Location location) {
        locations.remove(location);
    }

    @Override
    public List<Location> getAll() {
        return locations;
    }

    @Override
    public List<Location> getByName(String name) {
        List<Location> returnLocations = new ArrayList<>();
        for (Location l : locations) {
            if (l.getCity().equals(name)) {
                returnLocations.add(l);
            }
        }
        return returnLocations;
    }
}
