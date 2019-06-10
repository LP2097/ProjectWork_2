package thunderbytes.com.formulanews.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Season implements Serializable {
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
