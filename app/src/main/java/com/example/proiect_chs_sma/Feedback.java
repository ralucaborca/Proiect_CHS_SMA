package com.example.proiect_chs_sma;

public class Feedback {
    private String id_medic;
    private String nume_medic;
    private String caz_puls;
    private String feedback;

    public Feedback(){

    }

    public Feedback(String id_medic, String nume_medic, String caz_puls, String feedback){
        this.id_medic = id_medic;
        this.nume_medic = nume_medic;
        this.caz_puls = caz_puls;
        this.feedback = feedback;
    }

    public String getID() {
        return id_medic;
    }

    public void setID(String id_medic) {
        this.id_medic = id_medic;
    }

    public String getNume() {
        return nume_medic;
    }

    public void setNume(String nume_medic) {
        this.nume_medic = nume_medic;
    }

    public String getCaz() {
        return caz_puls;
    }

    public void setCaz(String caz_puls) {
        this.caz_puls = caz_puls;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
