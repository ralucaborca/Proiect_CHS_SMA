package com.example.proiect_chs_sma;

public class Doctor_details {
    protected String name, prenume, adresa_cabinet, specializare, email;
    private String  usertype;

    public Doctor_details(){}

    public Doctor_details(String name, String prenume, String specializare, String adresa_cabinet, String email, String  usertype){
        this.name = name;
        this.prenume = prenume;
        this.specializare = specializare;
        this.adresa_cabinet = adresa_cabinet;
        this.email = email;
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume){
        this.prenume = prenume;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare){
        this.specializare = specializare;
    }

    public String getAdresaCabinet() {
        return adresa_cabinet;
    }

    public void setAdresaCabinet(String adresa_cabinet){
        this.adresa_cabinet = adresa_cabinet;
    }


}
