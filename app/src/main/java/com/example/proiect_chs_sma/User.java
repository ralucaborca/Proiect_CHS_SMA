package com.example.proiect_chs_sma;

public class User {
    protected String fullname, email;
    private String  usertype;

    public User(){}

    public User(String fullname, String email, String  usertype){
        this.fullname = fullname;
        this.email = email;
        this.usertype = usertype;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname){
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {return usertype;}

    public void setUsertype(String usertype) { this.usertype = usertype;}

}
