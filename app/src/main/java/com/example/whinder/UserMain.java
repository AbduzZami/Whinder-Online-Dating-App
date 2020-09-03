package com.example.whinder;

public class UserMain {
    String sex ;
    String username ;

    public UserMain(String sex, String username) {
        this.sex = sex;
        this.username = username ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserMain() {
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
