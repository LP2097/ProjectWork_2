package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Driver;

@Dao
public interface DriverDao {
    @Query("SELECT * FROM Driver")
    List<Driver> getAll();


    @Query("SELECT * FROM Driver WHERE driverId = :id")
    Driver getById(String id);

    @Insert
    void insertAll(Driver ...drivers);

    @Insert
    void insert(Driver driver);

    @Delete
    void delete(Driver driver);
}
