package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.FastestLap;

@Dao
public interface FastestLapDao {
    @Query("SELECT * FROM FastestLap")
    List<FastestLap> getAll();


    @Query("SELECT * FROM FastestLap WHERE fastestLapId = :id")
    FastestLap getById(String id);

    @Insert
    void insertAll(FastestLap ...fastestLaps);

    @Insert
    void insert(FastestLap fastestLap);

    @Delete
    void delete(FastestLap fastestLap);
}
