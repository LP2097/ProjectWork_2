package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
