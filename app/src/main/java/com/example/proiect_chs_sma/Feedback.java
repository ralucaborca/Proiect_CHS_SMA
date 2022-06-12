package com.example.proiect_chs_sma;

public class Feedback {
    private String nume_medic;
    private String caz_puls;
    private String feedback;
    private String id_pacient;
    public Feedback(){

    }

    public Feedback(String nume_medic, String caz_puls, String feedback, String id_pacient){
        this.id_pacient = id_pacient;
        this.nume_medic = nume_medic;
        this.caz_puls = caz_puls;
        this.feedback = feedback;
    }

    public String getIDPacient() {
        return id_pacient;
    }

    public void setIDPacient(String id_pacient) {
        this.id_pacient = id_pacient;
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
