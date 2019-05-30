package thunderbytes.com.formulanews.Wrappers;

import java.util.ArrayList;

import thunderbytes.com.formulanews.Models.Standings;

public class StandingsTable {
    private int season;
    public ArrayList<Standings> StandingsLists;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public ArrayList<Standings> getStandingsLists() {
        return StandingsLists;
    }

    public void setStandingsLists(ArrayList<Standings> standingsLists) {
        StandingsLists = standingsLists;
    }
}
