package com.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Niek on 2-3-2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Account.findAll",
                query = "SELECT u FROM User u"),
        @NamedQuery(name = "Account.findByUsername",
                query = "SELECT u FROM User u where u.userName = :userName"),
        @NamedQuery(name = "Account.getByUserNameContains",
                query = "SELECT u FROM User u where u.userName like :partOfUsername")
})
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    private String bio;
    private String website;
    private String pictureUrl;


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "rolename", referencedColumnName = "rolename"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "rolename"})})
    private Collection<Role> roles;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @ManyToMany
    private List<User> following;



    @ManyToMany
    @JoinTable(name = "user_followers"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "ID", nullable = false))
    private List<User> followers;


    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
    private List<Kweet> kweets;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public User(String userName, String password, String bio, Location location, String website, String pictureUrl) {
        this.userName = userName;
        this.password = password;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.pictureUrl = pictureUrl;
        this.following = new ArrayList<>();
        this.kweets = new ArrayList<>();
        this.followers = new ArrayList<>();
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

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Kweet> getKweets() {
        Collections.sort(kweets);
        Collections.reverse(kweets);
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    /**
     * @param kweet
     */
    public void addKweet(Kweet kweet) {
        if (kweet != null && kweets != null) {
            kweets.add(kweet);
//            if (kweet.getPoster() != this)
//                kweet.setPoster(this);
        }
    }

    /**
     * @param kweet
     */
    public void removeKweet(Kweet kweet) {
        if (kweet != null && kweets != null && kweets.contains(kweet)) {
            this.kweets.remove(kweet);
        }
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> groups) {
        this.roles = groups;
    }

    public void clearRoles() {
        roles.clear();
    }

    public void addRole(Role role) {
        if (role != null && this.roles != null && !this.roles.contains(role)) {
            this.roles.add(role);
            if (!role.getUsers().contains(this))
                role.addUsers(this);
        }
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<User> getFollowers() {
        return followers;
    }

    /**
     * @param follow
     */
    public void addFollowing(User follow) {
        if (follow != null && following != null) {
            following.add(follow);
            follow.addFollower(this);
        }
    }

    private void addFollower(User user) {
        followers.add(user);
    }

    /**
     * @param follow
     */
    public void removeFollowing(User follow) {
        for (User u:following) if(u.getUserName().equals(follow.getUserName())) {
            following.remove(u);
            break;
        }
        follow.removeFollower(this);
    }

    public void removeFollower(User follower) {
        for (User u:followers) if(u.getUserName().equals(follower.getUserName())) {
            followers.remove(u);
            break;
        }
    }

    public void removeFollowers(){
        ArrayList<User> userss = new ArrayList<>();
        for (User u:followers){
            userss.add(u);
        }
        for (User u:userss){
            u.removeFollowing(this);
        }
    }



}
