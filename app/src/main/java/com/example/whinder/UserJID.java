package com.example.whinder;

public class UserJID {
    private String id ;
    private String username ;

    public UserJID(String id , String username) {
        this.id = id;
        this.username = username ;
    }

    public UserJID() {
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
}
