package com.example.proiect_chs_sma;

public class PhotoDatas {
    private String linkImagine;
    protected String idPacient;
    public PhotoDatas(){
    }

    public PhotoDatas(String linkImagine, String idPacient) {
        this.linkImagine = linkImagine;
        this.idPacient = idPacient;
    }

    public String getLinkImagine() {
        return linkImagine;
    }

    public void setLinkImagine(String linkImagine) {
        this.linkImagine = linkImagine;
    }

    public String getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(String idPacient) {
        this.idPacient = idPacient;
    }

}
