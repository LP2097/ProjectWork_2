package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import thunderbytes.com.formulanews.Models.AverageSpeed;

@Dao
public interface AverageSpeedDao {
    @Query("SELECT * FROM AverageSpeed")
    List<AverageSpeed> getAll();


    @Query("SELECT * FROM AverageSpeed WHERE averageSpeedId = :id")
    AverageSpeed getById(String id);

    @Insert
    void insertAll(AverageSpeed ...averageSpeeds);

    @Insert
    void insert(AverageSpeed averageSpeed);

    @Delete
    void delete(AverageSpeed averageSpeed);
}
