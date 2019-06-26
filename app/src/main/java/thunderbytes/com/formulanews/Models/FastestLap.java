package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
    @ForeignKey(entity = Time.class, parentColumns = "timeId", childColumns = "timeId"),
    @ForeignKey(entity = AverageSpeed.class, parentColumns = "averageSpeedId", childColumns = "averageSpeedId")
})
public class FastestLap {

    @PrimaryKey(autoGenerate = true)
    public int fastestLapId;

    @ColumnInfo
    private int rank;

    @ColumnInfo
    private int lap;

    @ColumnInfo(index = true)
    public int timeId;

    @Ignore
    public Time Time;

    @ColumnInfo(index = true)
    public int averageSpeedId;

    @Ignore
    public AverageSpeed AverageSpeed;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public Time getTime() {
        return Time;
    }

    public void setTime(Time time) {
        Time = time;
    }

    public AverageSpeed getAverageSpeed() {
        return AverageSpeed;
    }

    public void setAverageSpeed(AverageSpeed averageSpeed) {
        AverageSpeed = averageSpeed;
    }
}
