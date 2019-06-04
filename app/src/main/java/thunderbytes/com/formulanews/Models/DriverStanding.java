package thunderbytes.com.formulanews.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class DriverStanding implements Serializable {
    private int position;
    private String positionText;
    private int points;
    private int wins;
    public Driver Driver;
    public ArrayList<Constructor> Constructors;

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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Driver getDriver() {
        return Driver;
    }

    public void setDriver(Driver driver) {
        this.Driver = driver;
    }

    public ArrayList<Constructor> getConstructors() {
        return Constructors;
    }

    public void setConstructors(ArrayList<Constructor> constructors) {
        Constructors = constructors;
    }

}
