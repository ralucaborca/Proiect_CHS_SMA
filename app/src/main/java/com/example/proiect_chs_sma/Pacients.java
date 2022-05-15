package com.example.proiect_chs_sma;

public class Pacients {
    protected String idPacient;
    protected String varsta;
    protected String greutate;
    protected String puls;
    protected String inaltime;
    protected String fumat;
    protected String sport;
    protected String sanatate;
    protected String nume_poza;
    protected String linkPoza;

    public Pacients() {
    }

    public Pacients(String idPacient, String varsta, String greutate, String puls, String inaltime, String fumat, String sport, String sanatate, String nume_poza, String linkPoza) {
        this.idPacient = idPacient;
        this.varsta = varsta;
        this.greutate = greutate;
        this.puls = puls;
        this.inaltime = inaltime;
        this.fumat = fumat;
        this.sport = sport;
        this.sanatate = sanatate;
        this.nume_poza = nume_poza;
        this.linkPoza = linkPoza;
    }
    public String getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(String idPacient) {
        this.idPacient = idPacient;
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

    public String getFumat() { return fumat;}

    public void setFumat(String fumat) { this.fumat = fumat;}

    public String getSport() { return sport;}

    public void setSport(String sport) {this.sport = sport;}

    public String getSanatate() {
        return sanatate;
    }

    public void setSanatate(String sanatate) {
        this.sanatate = sanatate;
    }

    public String getNumePoza() {
        return nume_poza;
    }

    public void setNumePoza(String nume_poza) {
        this.nume_poza = nume_poza;
    }

    public String getLinkImagine1() {
        return linkPoza;
    }

    public void setLinkImagine(String linkPoza) {
        this.linkPoza = linkPoza;
    }
}
