package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.DriverStanding;

@Dao
public interface DriverStandingDao {
    @Query("SELECT * FROM DriverStanding")
    List<DriverStanding> getAll();


    @Query("SELECT * FROM DriverStanding WHERE driverStandingId = :id")
    DriverStanding getById(String id);

    @Insert
    void insertAll(DriverStanding ...driverStandings);

    @Insert
    void insert(DriverStanding driverStanding);

    @Delete
    void delete(DriverStanding driverStanding);
}
