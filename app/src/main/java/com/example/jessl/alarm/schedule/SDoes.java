package com.example.jessl.alarm.schedule;

public class SDoes {
    String iddoes;
    String descdoes;
    String datedoes;
    String keydoes;

    public SDoes() {
    }

    public SDoes(String iddoes, String descdoes, String datedoes,String keydoes) {
        this.iddoes = iddoes;
        this.datedoes = datedoes;
        this.descdoes = descdoes;
        this.keydoes = keydoes;
    }

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    public String getIddoes() {
        return iddoes;
    }

    public void setIddoes(String iddoes) {
        this.iddoes = iddoes;
    }

    public String getDatedoes() {
        return datedoes;
    }

    public void setDatedoes(String datedoes) {
        this.datedoes = datedoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }
}
