package thunderbytes.com.formulanews.Models;

public class Circuit {
    private String circuitId;
    private String url;
    private String circuitName;
    public Location Location;

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

    public Location getLocation() {
        return Location;
    }

    public void setLocation(Location location) {
        this.Location = location;
    }

    @Override
    public String toString() {
        return "Circuit{}";
    }
}
