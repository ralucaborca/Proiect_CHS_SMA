package com.example.proiect_chs_sma;

public class Pacients {
    private String CNP;
    private String varsta;
    private String greutate;
    private String puls;
    private String inaltime;
    private String sanatate;

    public Pacients() {
    }

    public Pacients(String CNP, String varsta, String greutate, String puls, String inaltime, String sanatate) {
        this.CNP = CNP;
        this.varsta = varsta;
        this.greutate = greutate;
        this.puls = puls;
        this.inaltime = inaltime;
        this.sanatate = sanatate;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getVarsta() {
        return varsta;
    }

    public void setVarsta(String varsta) {
        this.varsta = varsta;
    }

    public String getGreutate() {
        return greutate;
    }

    public void setGreutate(String greutate) {
        this.greutate = greutate;
    }

    public String getPuls() {
        return puls;
    }

    public void setPuls(String puls) {
        this.puls = puls;
    }

    public String getInaltime() {
        return inaltime;
    }

    public void setInaltime(String inaltime) {
        this.inaltime = inaltime;
    }

    public String getSanatate() {
        return sanatate;
    }

    public void setSanatate(String sanatate) {
        this.sanatate = sanatate;
    }
}
