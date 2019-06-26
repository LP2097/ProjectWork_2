package thunderbytes.com.formulanews.Daos;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.Time;

@Dao
public interface TimeDao {
    @Query("SELECT * FROM Time")
    List<Time> getAll();


    @Query("SELECT * FROM Time WHERE timeId = :id")
    Time getById(String id);

    @Insert
    void insertAll(Time ...times);

    @Insert
    void insert(Time time);

    @Delete
    void delete(Time time);
}
