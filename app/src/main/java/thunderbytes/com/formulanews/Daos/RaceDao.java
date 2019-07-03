package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;
import thunderbytes.com.formulanews.Models.Race;

@Dao
public interface RaceDao {
    @Query("SELECT * FROM Race")
    List<Race> getAll();


    @Query("SELECT * FROM Race WHERE Id = :id")
    Race getById(String id);

    @Insert
    void insertAll(Race ...races);

    @Insert
    void insert(Race race);

    @Delete
    void delete(Race race);

}
