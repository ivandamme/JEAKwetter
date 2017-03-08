package com.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Niek on 3-3-2017.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Location.findAll",
                query = "SELECT l FROM Location l"),
        @NamedQuery(name = "Location.findByName",
                query = "SELECT l FROM Location l where l.city = :cityName")
})
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long longitude;
    private long latitude;
    private String city;

    public Location() {
    }

    public Location(long longitude, long latitude, String city) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
