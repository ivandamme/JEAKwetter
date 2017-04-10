package com.kwetter.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * Created by Niek on 2-3-2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Kweet.findAll",
                query = "SELECT k FROM Kweet k"),
        @NamedQuery(name = "Kweet.getById",
                query = "SELECT k FROM Kweet k where k.id = :id"),
        @NamedQuery(name = "Kweet.getByText",
                query = "SELECT k FROM Kweet k where k.text like :text")
})


public class Kweet implements Serializable, Comparable<Kweet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    private String text;


    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date date;

    @ManyToOne(optional = false)
    private User poster;

    public Kweet() {

    }

    public Kweet(String text, User poster) {
        this.text = text;
        this.poster = poster;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String content) {
        this.text = content;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getPoster() {
        return poster;
    }

//    public void setPoster(User poster) {
//        this.poster = poster;
//    }

    public String getUsername()
    {
        return poster.getUserName();
    }

    public void setUsername(String username)
    {
        this.poster.setUserName(username);
    }

    @Override
    public int compareTo(Kweet o) {
        return getDate().compareTo(o.getDate());
    }

    public String getTimeDifference() {
        String returnString = "";
        Date date2 = new Date();
        long diff = date2.getTime() - this.date.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) ((date2.getTime() - this.date.getTime()) / (1000 * 60 * 60 * 24));

        if (diffHours < 1) {
            if (diffMinutes < 1) {
                returnString = " Just now";
                return returnString;
            }
            returnString = diffMinutes + " minutes ago";
        } else if (diffHours > 24) {
            returnString = diffInDays + " days ago";
        } else {
            returnString = diffHours + " hours ago";
        }

        return returnString;
    }


}
