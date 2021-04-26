package com.example.rebound.data;

public class Team_data {
    String naem;
    String area;
    String manager;
    String coch;
    String temabirth;
    String uri;

    public Team_data(String naem, String area, String manager, String coch, String temabirth, String uri) {
        this.naem = naem;
        this.area = area;
        this.manager = manager;
        this.coch = coch;
        this.temabirth = temabirth;
        this.uri = uri;
    }

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCoch() {
        return coch;
    }

    public void setCoch(String coch) {
        this.coch = coch;
    }

    public String getTemabirth() {
        return temabirth;
    }

    public void setTemabirth(String temabirth) {
        this.temabirth = temabirth;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
