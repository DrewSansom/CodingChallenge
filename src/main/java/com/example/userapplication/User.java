package com.example.userapplication;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * The user class that will be saved in the db through JPA
 * This class provides getters and se
 */
@Entity
public class User {

    // Id is generated automatically if needed
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;

    /**
     * Basic argument free constructor
     */
    public User() {
    }


    /**
     * Constructor for a user whose ID will be generated automatically.
     * @param firstName The users first name, case sensitive
     * @param lastName The users last name, case sensitive
     */
    public User(String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     *
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param name first name to set
     */
    public void setFirstName(String name) {
        this.firstName = name;
    }

    /**
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param name last name to set
     */
    public void setLastName(String name) {
        this.lastName = name;
    }


    /**
     * Overrides object equals method to compare 2 User objects
     * @param obj The object to compare to the calling object
     * @return True if the objects are equal or if there values are equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if ( !(obj instanceof User)) {
            return false;
        }

        User user = (User) obj;
        return Objects.equals(this.id, user.id) && Objects.equals(this.firstName, user.firstName)
                && Objects.equals(this.lastName, user.lastName);
    }

    /**
     *
     * @return String of all data in object
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", role='" + lastName + '\'' +
                '}';
    }

}
