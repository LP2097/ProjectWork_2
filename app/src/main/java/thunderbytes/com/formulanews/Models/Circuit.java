package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Location.class, parentColumns = "locationId", childColumns = "locationId"))
public class Circuit implements Serializable {

    @PrimaryKey @NonNull
    private String circuitId;

    @ColumnInfo
    private String url;

    @ColumnInfo
    private String circuitName;

    @ColumnInfo
    public int locationId;
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
