package thunderbytes.com.formulanews.Models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Location.class, parentColumns = "locationId", childColumns = "locationId"))
public class Circuit implements Serializable {

    @PrimaryKey
    @NonNull
    private String circuitId;

    @ColumnInfo
    private String url;

    @ColumnInfo
    private String circuitName;


    @ColumnInfo(index = true)
    public int locationId;

    @Ignore
    public Location location;

    public String getCircuitId() {
        return circuitId;
    }

    public void setCircuitId(String circuitId) {
        this.circuitId = circuitId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    @JsonGetter("Location")
    public Location getLocation() {
        return location;
    }

    @JsonSetter("Location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Circuit{}";
    }
}
