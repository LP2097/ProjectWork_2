package thunderbytes.com.formulanews.Wrappers;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Models.Race;

public class RaceTable {
    private String season;
    public ArrayList<Race> Races;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<Race> getRaces() {
        return Races;
    }

    public void setRaces(ArrayList<Race> races) {
        this.Races = races;
    }
}
