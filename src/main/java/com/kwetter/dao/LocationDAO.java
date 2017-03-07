package com.kwetter.dao;

import com.kwetter.model.Location;
import com.kwetter.model.User;

import java.util.List;

/**
 * Created by Niek on 6-3-2017.
 */
public interface LocationDAO {

    /**
     * Creates a new Location object
     *
     * @param location
     */
    void createLocation(Location location);

    /**
     * Edits a location object
     *
     * @param location
     */
    void editLocation(Location location);

    /**
     * Removes a location object
     *
     * @param location
     */
    void removeLocation(Location location);






}
