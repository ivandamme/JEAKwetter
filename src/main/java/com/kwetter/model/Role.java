package com.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Niek on 7-3-2017.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    public Role() {}


    @Id
    @Column(name = "rolename")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
