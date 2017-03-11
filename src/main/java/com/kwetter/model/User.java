package com.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 2-3-2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findAll",
                query = "SELECT u FROM User u"),
        @NamedQuery(name = "Account.findByUsername",
                query = "SELECT u FROM User u where u.userName = :userName")
})
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String userName;
    private String password;
    private String bio;
    private String website;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Role role;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @ManyToMany
    private List<User> following;

    @OneToMany(mappedBy = "poster",cascade = CascadeType.ALL)
    private List<Kweet> kweets;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public User(String userName, String password, String bio, Location location, String website,Role role) {
        this.userName = userName;
        this.password = password;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.following = new ArrayList<>();
        this.kweets = new ArrayList<>();
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password_hash) {
        this.password = password_hash;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    /**
     *
     * @param kweet
     */
    public void addKweet(Kweet kweet) {
        if (kweet != null && kweets != null) {
            kweets.add(kweet);
            if (kweet.getPoster() != this)
                kweet.setPoster(this);
        }
    }

    /**
     *
     * @param kweet
     */
    public void removeKweet(Kweet kweet) {
        if (kweet != null && kweets != null && kweets.contains(kweet)) {
            this.kweets.remove(kweet);
        }
    }

    /**
     *
     * @param follow
     */
    public void addFollowing(User follow) {
        if (follow != null && following != null)
        this.following.add(follow);
    }

    /**
     *
     * @param follow
     */
    public void removeFollowing(User follow) {
        this.following.remove(follow);
    }




}
