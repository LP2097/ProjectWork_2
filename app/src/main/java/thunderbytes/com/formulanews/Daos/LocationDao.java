package thunderbytes.com.formulanews.Daos;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import thunderbytes.com.formulanews.Models.Location;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM Location")
    List<Location> getAll();

    @Query("DELETE FROM Location")
    void deleteAll();

    @Query("SELECT * FROM Location WHERE locationId = :id")
    Location getById(String id);

    @Insert
    void insertAll(Location...locations);

    @Insert
    void insert(Location location);

    @Delete
    void delete(Location location);
}

