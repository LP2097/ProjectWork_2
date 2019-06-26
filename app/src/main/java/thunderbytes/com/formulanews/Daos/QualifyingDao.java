package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Qualifying;

@Dao
public interface QualifyingDao {
    @Query("SELECT * FROM Qualifying")
    List<Qualifying> getAll();


    @Query("SELECT * FROM Qualifying WHERE qualifyingId = :id")
    Qualifying getById(String id);

    @Insert
    void insertAll(Qualifying ...qualifyings);

    @Insert
    void insert(Qualifying qualifying);

    @Delete
    void delete(Qualifying qualifying);
}
