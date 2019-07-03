package thunderbytes.com.formulanews.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@Entity
public class Location implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int locationId;

    @ColumnInfo
    private Double latitude;

    @ColumnInfo
    private Double longitude;

    @ColumnInfo
    private String locality;

    @ColumnInfo
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
