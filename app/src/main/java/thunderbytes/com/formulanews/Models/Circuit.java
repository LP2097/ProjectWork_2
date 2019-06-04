package thunderbytes.com.formulanews.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class Circuit implements Serializable {
    private String circuitId;
    private String url;
    private String circuitName;
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
