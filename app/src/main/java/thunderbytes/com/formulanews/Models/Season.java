package thunderbytes.com.formulanews.Models;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "Season")
public class Season implements Serializable {


    @PrimaryKey
    private int seasonYear;

    @Ignore
    public ArrayList<Race> races;

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public ArrayList<Race> getRaces() { return races; }

    public void setRaces(ArrayList<Race> races) {
        this.races = races;
    }

}
