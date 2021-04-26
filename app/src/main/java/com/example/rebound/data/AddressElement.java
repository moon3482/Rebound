package com.example.rebound.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressElement {

    @SerializedName("types")
    @Expose
    private List<String> types = null;
    @SerializedName("longName")
    @Expose
    private String longName;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("code")
    @Expose
    private String code;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}