package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;
import thunderbytes.com.formulanews.Models.Standings;

@Dao
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
