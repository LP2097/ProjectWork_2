package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

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
