package thunderbytes.com.formulanews.Models;

import java.util.ArrayList;

public class Standings {
    private int season;
    private int round;
    public ArrayList<DriverStanding> DriverStandings;
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
