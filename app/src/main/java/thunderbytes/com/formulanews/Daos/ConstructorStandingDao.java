package thunderbytes.com.formulanews.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

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
}
