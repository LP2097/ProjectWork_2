package thunderbytes.com.formulanews.CacheManager;

import java.util.ArrayList;
import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.Models.Race;

public class CacheManager{

    private static final CacheManager instance = new CacheManager();

    //grae e qualif.
    private ArrayList<Race> races;

    //classifica costruttori
    private ArrayList<ConstructorStanding> constructors;

    //classifica piloti
    private ArrayList<DriverStanding> drivers;


    public CacheManager() { }

    public void setRaces(ArrayList<Race> races) {
        this.races = races;
    }

    public ArrayList<Race> getRaces() {
        return races;
    }


    public ArrayList<ConstructorStanding> getConstructors() {
        return constructors;
    }

    public void setConstructors(ArrayList<ConstructorStanding> constructors) {
        this.constructors = constructors;
    }

    public ArrayList<DriverStanding> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<DriverStanding> drivers) {
        this.drivers = drivers;
    }
}
