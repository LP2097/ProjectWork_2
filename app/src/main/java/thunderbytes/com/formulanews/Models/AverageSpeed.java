package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AverageSpeed {

    @PrimaryKey(autoGenerate = true)
    public int averageSpeedId;

    @ColumnInfo
    private String units;

    @ColumnInfo
    private String speed;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
