package com.example.proiect_chs_sma;

public class PhotoDatas {
    private String linkImagine;
    protected String idPacient;
    protected String numePoza;
    public PhotoDatas(){
    }

    public PhotoDatas(String linkImagine, String idPacient, String numePoza) {
        this.linkImagine = linkImagine;
        this.idPacient = idPacient;
        this.numePoza = numePoza;
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

    public String getNumePoza() {
        return numePoza;
    }

    public void setNumePoza(String numePoza) {
        this.numePoza = numePoza;
    }


}
