package thunderbytes.com.formulanews.Helpers;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import thunderbytes.com.formulanews.Daos.AverageSpeedDao;
import thunderbytes.com.formulanews.Daos.CircuitDao;
import thunderbytes.com.formulanews.Daos.ConstructorDao;
import thunderbytes.com.formulanews.Daos.ConstructorStandingDao;
import thunderbytes.com.formulanews.Daos.DriverDao;
import thunderbytes.com.formulanews.Daos.DriverStandingDao;
import thunderbytes.com.formulanews.Daos.FastestLapDao;
import thunderbytes.com.formulanews.Daos.LocationDao;
import thunderbytes.com.formulanews.Daos.QualifyingDao;
import thunderbytes.com.formulanews.Daos.RaceDao;
import thunderbytes.com.formulanews.Daos.ResultsDao;
import thunderbytes.com.formulanews.Daos.SeasonDao;
import thunderbytes.com.formulanews.Daos.StandingsDao;
import thunderbytes.com.formulanews.Daos.TimeDao;
import thunderbytes.com.formulanews.Models.AverageSpeed;
import thunderbytes.com.formulanews.Models.Circuit;
import thunderbytes.com.formulanews.Models.Constructor;
import thunderbytes.com.formulanews.Models.ConstructorStanding;
import thunderbytes.com.formulanews.Models.Driver;
import thunderbytes.com.formulanews.Models.DriverStanding;
import thunderbytes.com.formulanews.Models.FastestLap;
import thunderbytes.com.formulanews.Models.Location;
import thunderbytes.com.formulanews.Models.Qualifying;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.Models.Results;
import thunderbytes.com.formulanews.Models.Season;
import thunderbytes.com.formulanews.Models.Standings;
import thunderbytes.com.formulanews.Models.Time;
import thunderbytes.com.formulanews.RoomDataConverters.DateConverter;


@Database(entities = {
    AverageSpeed.class, Circuit.class, Constructor.class, ConstructorStanding.class,
    Driver.class, DriverStanding.class, FastestLap.class, Location.class, Qualifying.class,
    Race.class, Results.class, Season.class, Standings.class, Time.class
}, version = 1, exportSchema = false)

@TypeConverters({DateConverter.class})

public abstract class FNdb extends RoomDatabase {
    public static final String DATABASE_NAME = "FNDB";

    public abstract AverageSpeedDao averageSpeedDao();
    public abstract CircuitDao circuitDao();
    public abstract ConstructorDao constructorDao();
    public abstract ConstructorStandingDao constructorStandingDao();
    public abstract DriverDao driverDao();
    public abstract DriverStandingDao driverStandingDao();
    public abstract FastestLapDao fastestLapDao();
    public abstract LocationDao locationDao();
    public abstract QualifyingDao qualifyingDao();
    public abstract RaceDao raceDao();
    public abstract ResultsDao resultsDao();
    public abstract SeasonDao seasonDao();
    public abstract StandingsDao standingsDao();
    public abstract TimeDao timeDao();

}
