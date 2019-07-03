package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;
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
