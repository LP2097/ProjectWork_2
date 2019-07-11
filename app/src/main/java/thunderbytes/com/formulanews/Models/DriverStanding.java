package thunderbytes.com.formulanews.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "DriverStanding", foreignKeys = {
        @ForeignKey(entity = Standings.class, parentColumns = "standingId", childColumns = "standingId", onDelete = CASCADE),
        @ForeignKey(entity = Driver.class, parentColumns = "driverId", childColumns = "driverId", onDelete = CASCADE),
        @ForeignKey(entity = Constructor.class, parentColumns = "constructorId", childColumns = "constructorId", onDelete = CASCADE)
})
public class DriverStanding implements Serializable {

    @PrimaryKey
    public int driverStandingId;

    @ColumnInfo(index = true)
    public String constructorId;

    @ColumnInfo(index = true)
    public int standingId;

    @ColumnInfo
    private int position;

    @ColumnInfo
    private String positionText;

    @ColumnInfo
    private int points;

    @ColumnInfo
    private int wins;

    @ColumnInfo(index = true)
    public String driverId;

    @Ignore
    public Driver Driver;

    @Ignore
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
