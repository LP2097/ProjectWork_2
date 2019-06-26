package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


import thunderbytes.com.formulanews.Models.Location;

@Dao
public interface CircuitDao {
    @Query("SELECT * FROM Location")
    List<Location> getAll();


    @Query("SELECT * FROM Location WHERE locationId = :id")
    Location getById(String id);

    @Insert
    void insertAll(Location ...locations);

    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);
}
