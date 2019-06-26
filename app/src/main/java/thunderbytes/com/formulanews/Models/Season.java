package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Season implements Serializable {

    @PrimaryKey
    private int seasonYear;

    public ArrayList<Race> races;

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public ArrayList<Race> getRaces() {
        return races;
    }

    public void setRaces(ArrayList<Race> races) {
        this.races = races;
    }

}
