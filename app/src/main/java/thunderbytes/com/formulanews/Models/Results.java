package thunderbytes.com.formulanews.Models;

public class Results {
    private int number;
    private int position;
    private String positionText;
    private int points;
    public Driver Driver;
    public Constructor Constructor;
    private int grid;
    private int laps;
    private String status;
    public Time Time;
    public FastestLap FastestLap;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Driver getDriver() {
        return Driver;
    }

    public void setDriver(Driver driver) {
        this.Driver = driver;
    }

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.Constructor = constructor;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getTime() {
        return Time;
    }

    public void setTime(Time time) {
        this.Time = time;
    }

    public FastestLap getFastestLap() {
        return FastestLap;
    }

    public void setFastestLap(FastestLap fastestLap) {
        this.FastestLap = fastestLap;
    }
}
