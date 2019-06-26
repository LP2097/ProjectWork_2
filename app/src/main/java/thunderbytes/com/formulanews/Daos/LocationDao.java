package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Circuit;

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

