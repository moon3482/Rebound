package com.example.rebound.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Address {

    @SerializedName("roadAddress")
    @Expose
    private String roadAddress;
    @SerializedName("jibunAddress")
    @Expose
    private String jibunAddress;
    @SerializedName("englishAddress")
    @Expose
    private String englishAddress;
    @SerializedName("addressElements")
    @Expose
    private List<AddressElement> addressElements = null;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("distance")
    @Expose
    private Double distance;

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getJibunAddress() {
        return jibunAddress;
    }

    public void setJibunAddress(String jibunAddress) {
        this.jibunAddress = jibunAddress;
    }

    public String getEnglishAddress() {
        return englishAddress;
    }

    public void setEnglishAddress(String englishAddress) {
        this.englishAddress = englishAddress;
    }

    public List<AddressElement> getAddressElements() {
        return addressElements;
    }

    public void setAddressElements(List<AddressElement> addressElements) {
        this.addressElements = addressElements;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
