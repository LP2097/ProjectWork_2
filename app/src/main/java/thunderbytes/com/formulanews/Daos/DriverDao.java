package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;
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

    @Query("DELETE FROM Driver")
    void deleteAll();
}
