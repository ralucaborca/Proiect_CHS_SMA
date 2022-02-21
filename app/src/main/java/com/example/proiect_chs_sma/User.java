package com.example.proiect_chs_sma;

public class User {
    protected String fullname, email;

    public User(){}

    public User(String fullname, String email){
        this.fullname = fullname;
        this.email = email;
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

}
