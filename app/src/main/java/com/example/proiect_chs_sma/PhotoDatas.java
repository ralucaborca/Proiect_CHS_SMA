package com.example.proiect_chs_sma;

public class PhotoDatas {
    String linkImagine;
    public PhotoDatas(){
    }

    public PhotoDatas(String linkImagine, String descriere) {
        this.linkImagine = linkImagine;

    }

    public String getLinkImagine() {
        return linkImagine;
    }

    public void setLinkImagine(String linkImagine) {
        this.linkImagine = linkImagine;
    }

}
