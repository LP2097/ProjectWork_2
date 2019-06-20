package thunderbytes.com.formulanews.Managers;

import android.arch.persistence.room.Room;
import android.content.Context;
import thunderbytes.com.formulanews.Helpers.FNdb;

public class DbManager {
    public static FNdb createDb(Context context){
        return Room.databaseBuilder(context, FNdb.class, FNdb.DATABASE_NAME).build();
    };
}
