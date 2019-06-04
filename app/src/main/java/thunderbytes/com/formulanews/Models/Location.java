package thunderbytes.com.formulanews.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class Location implements Serializable {

    private Double latitude;
    private Double longitude;
    private String locality;
    private String country;

    @JsonGetter("lat")
    public Double getLatitude() {
        return latitude;
    }

    @JsonSetter("lat")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonGetter("long")
    public Double getLongitude() {
        return longitude;
    }

    @JsonSetter("long")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{}";
    }
}
