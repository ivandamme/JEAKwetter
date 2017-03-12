package com.kwetter.model;

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
                query = "SELECT k FROM Kweet k where k.id = :id")
})
public class Kweet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;



    @Temporal(javax.persistence.TemporalType.TIMESTAMP)

    private Date date;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
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
//        if (!poster.getKweets().contains(this))
//            poster.addKweet(this);
//    }


}
