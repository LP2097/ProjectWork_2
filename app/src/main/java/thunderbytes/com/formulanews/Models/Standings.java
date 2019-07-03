package thunderbytes.com.formulanews.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Standings implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int standingId;

    @ColumnInfo
    private int season;

    @ColumnInfo
    public int round;

    @Ignore
    public ArrayList<DriverStanding> DriverStandings;

    @Ignore
    public ArrayList<ConstructorStanding> ConstructorStandings;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ArrayList<DriverStanding> getDriverStandings() {
        return DriverStandings;
    }

    public void setDriverStandings(ArrayList<DriverStanding> driverStandings) {
        DriverStandings = driverStandings;
    }

    public ArrayList<ConstructorStanding> getConstructorStandings() {
        return ConstructorStandings;
    }

    public void setConstructorStandings(ArrayList<ConstructorStanding> constructorStandings) {
        ConstructorStandings = constructorStandings;
    }
}
