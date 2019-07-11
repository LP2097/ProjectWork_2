package thunderbytes.com.formulanews.Daos;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Circuit;
import thunderbytes.com.formulanews.Models.Location;

@Dao
public interface CircuitDao {
    @Query("SELECT * FROM Circuit")
    List<Circuit> getAll();

    @Query("DELETE FROM Circuit")
    void deleteAll();

    @Query("SELECT * FROM Circuit WHERE circuitId = :id")
    Circuit getById(String id);

    @Insert
    void insertAll(Circuit... circuit);

    @Insert
    void insert(Circuit circuit);

    @Delete
    void delete(Circuit circuit);
}
