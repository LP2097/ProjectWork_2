package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

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
