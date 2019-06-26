package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Season;

@Dao
public interface SeasonDao {
    @Query("SELECT * FROM Season")
    List<Season> getAll();


    @Query("SELECT * FROM Season WHERE seasonYear = :id")
    Season getById(String id);

    @Insert
    void insertAll(Season ...seasons);

    @Insert
    void insert(Season season);

    @Delete
    void delete(Season season);
}
