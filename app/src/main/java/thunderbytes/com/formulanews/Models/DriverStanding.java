package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(foreignKeys = {
        @ForeignKey(entity = Standings.class, parentColumns = "standingId", childColumns = "standingId"),
        @ForeignKey(entity = Driver.class, parentColumns = "driverId", childColumns = "driverId")
})
public class DriverStanding implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int driverStandingId;

    @ColumnInfo
    private int position;

    @ColumnInfo
    private String positionText;

    @ColumnInfo
    private int points;

    @ColumnInfo
    private int wins;

    @ColumnInfo
    public String driverId;
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
