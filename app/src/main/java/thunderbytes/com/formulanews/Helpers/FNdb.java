package thunderbytes.com.formulanews.Helpers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import thunderbytes.com.formulanews.Daos.ConstructorDao;
import thunderbytes.com.formulanews.Models.Constructor;

@Database(entities = {Constructor.class}, version = 1)
public abstract class FNdb extends RoomDatabase {
    public static final String DATABASE_NAME = "FNDB";

    public abstract ConstructorDao constructorDao();
}
