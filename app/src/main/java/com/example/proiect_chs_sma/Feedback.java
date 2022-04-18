package com.example.proiect_chs_sma;

public class Feedback {
    private String id_medic;
    private String nume_medic;
    private String caz_puls;
    private String feedback;
    private String id_pacient;
    private String data_ora;
    public Feedback(){

    }

    public Feedback(String id_medic, String nume_medic, String caz_puls, String feedback, String id_pacient, String data_ora){
        this.id_medic = id_medic;
        this.id_pacient = id_pacient;
        this.nume_medic = nume_medic;
        this.caz_puls = caz_puls;
        this.feedback = feedback;
        this.data_ora = data_ora;
    }

    public String getID() {
        return id_medic;
    }

    public void setID(String id_medic) {
        this.id_medic = id_medic;
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

    public String getDataOra() {
        return data_ora;
    }

    public void setDataOra(String data_ora) {
        this.data_ora = data_ora;
    }
}
