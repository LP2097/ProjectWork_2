package thunderbytes.com.formulanews.Models;

import java.util.ArrayList;

public class Season {
    private int seasonYear;
    private ArrayList<Race> races;

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
