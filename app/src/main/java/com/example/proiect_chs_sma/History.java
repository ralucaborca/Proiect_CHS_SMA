package com.example.proiect_chs_sma;

public class History {
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

    public History() {
    }

    public History(String idPacient, String varsta, String greutate, String puls, String inaltime, String fumat, String sport, String sanatate, String nume_poza, String linkPoza) {
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

    public void setIdPacient1(String idPacient) {
        this.idPacient = idPacient;
    }

    public String getVarsta1() {
        return varsta;
    }

    public void setVarsta1(String varsta) {
        this.varsta = varsta;
    }

    public String getGreutate1() {
        return greutate;
    }

    public void setGreutate1(String greutate) {
        this.greutate = greutate;
    }

    public String getPuls1() {
        return puls;
    }

    public void setPuls1(String puls) {
        this.puls = puls;
    }

    public String getInaltime1() {
        return inaltime;
    }

    public void setInaltime1(String inaltime) {
        this.inaltime = inaltime;
    }

    public String getFumat1() { return fumat;}

    public void setFumat1(String fumat) { this.fumat = fumat;}

    public String getSport1() { return sport;}

    public void setSport1(String sport) {this.sport = sport;}

    public String getSanatate1() {
        return sanatate;
    }

    public void setSanatate1(String sanatate) {
        this.sanatate = sanatate;
    }

    public String getNumePoza1() {
        return nume_poza;
    }

    public void setNumePoza1(String nume_poza) {
        this.nume_poza = nume_poza;
    }

}
