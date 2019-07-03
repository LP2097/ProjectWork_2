package thunderbytes.com.formulanews.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import thunderbytes.com.formulanews.Models.Constructor;

@Dao
public interface ConstructorDao {
    @Query("SELECT * FROM Constructor")
    List<Constructor> getAll();


   @Query("SELECT * FROM Constructor WHERE constructorId = :id")
    Constructor getById(String id);

   @Insert
    void insertAll(Constructor ...constructors);

   @Insert
    void insert(Constructor constructor);

    @Delete
    void delete(Constructor constructor);
}
