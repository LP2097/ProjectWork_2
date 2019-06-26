package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Standings;

public interface StandingsDao {
    @Query("SELECT * FROM Standings")
    List<Standings> getAll();


    @Query("SELECT * FROM Standings WHERE standingId = :id")
    Standings getById(String id);

    @Insert
    void insertAll(Standings ...standings);

    @Insert
    void insert(Standings standings);

    @Delete
    void delete(Standings standings);
}
