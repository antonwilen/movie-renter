package se.yrgo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String title;
    private boolean rented;

    public Movie() {
    }

    public void setTitle(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "hejhej";
    }



    public boolean isRented() {
        return rented;
    }



    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
