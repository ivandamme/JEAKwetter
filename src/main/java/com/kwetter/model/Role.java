package com.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 7-3-2017.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    public Role() {
        users = new ArrayList<>();
    }


    @Id
    @Column(name = "rolename")
    private String roleName;

    @ManyToMany
    @JoinTable(name="USERS_ROLES"
            , joinColumns = @JoinColumn(name = "rolename", referencedColumnName = "rolename")
            , inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username"))
    private List<User> users;


    public Role(String roleName) {
        this.roleName = roleName;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<User> getUsers() {
        return users;
    }


    public void addUsers(User user) {
        if (user != null && users != null && !users.contains(user)) {
            users.add(user);
            if (!user.getRoles().contains(this))
                user.addRole(this);
        }
    }


}
