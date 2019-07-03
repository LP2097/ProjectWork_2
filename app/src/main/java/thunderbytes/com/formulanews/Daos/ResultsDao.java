package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;
import thunderbytes.com.formulanews.Models.Results;

@Dao
public interface ResultsDao {

    @Query("SELECT * FROM Results")
    List<Results> getAll();

    @Query("SELECT * FROM Results WHERE resultId = :id")
    Results getById(String id);

    @Insert
    void insertAll(Results ...results);

    @Insert
    void insert(Results result);

    @Delete
    void delete(Results result);
}
