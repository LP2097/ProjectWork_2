package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;
import thunderbytes.com.formulanews.Models.ConstructorStanding;

@Dao
public interface ConstructorStandingDao {
    @Query("SELECT * FROM ConstructorStanding")
    List<ConstructorStanding> getAll();


    @Query("SELECT * FROM ConstructorStanding WHERE constructorStandingId = :id")
    ConstructorStanding getById(String id);

    @Insert
    void insertAll(ConstructorStanding ...constructorStandings);

    @Insert
    void insert(ConstructorStanding constructorStanding);

    @Delete
    void delete(ConstructorStanding constructorStanding);

    @Query("DELETE FROM ConstructorStanding")
    void deleteAll();
}
