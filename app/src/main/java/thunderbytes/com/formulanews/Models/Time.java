package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Time {

    @PrimaryKey(autoGenerate = true)
    public int timeId;

    @ColumnInfo
    private int millis;

    @ColumnInfo
    private String time;

    public int getMillis() {
        return millis;
    }

    public void setMillis(int millis) {
        this.millis = millis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
