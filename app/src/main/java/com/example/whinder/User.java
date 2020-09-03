package com.example.whinder;

public class User {
    String username , email , interest , id;

    public User(String username, String email, String interest , String id) {
        this.username = username;
        this.email = email;
        this.interest = interest;
        this.id = id ;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
