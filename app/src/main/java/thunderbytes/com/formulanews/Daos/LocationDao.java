package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import thunderbytes.com.formulanews.Models.Circuit;
import thunderbytes.com.formulanews.Models.Constructor;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM Circuit")
    List<Circuit> getAll();


    @Query("SELECT * FROM Circuit WHERE circuitId = :id")
    Circuit getById(String id);

    @Insert
    void insertAll(Circuit ...circuits);

    @Insert
    void insert(Circuit circuit);

    @Delete
    void delete(Circuit circuit);
}

