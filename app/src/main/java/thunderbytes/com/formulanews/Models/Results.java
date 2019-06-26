package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
    @ForeignKey(entity = Race.class, parentColumns = "Id", childColumns = "raceId"),
    @ForeignKey(entity = Driver.class, parentColumns = "driverId", childColumns = "driverId"),
    @ForeignKey(entity = Constructor.class, parentColumns = "constructorId", childColumns = "constructorId"),
    @ForeignKey(entity = Time.class, parentColumns = "timeId", childColumns = "timeId"),
    @ForeignKey(entity = FastestLap.class, parentColumns = "fastestLapId", childColumns = "fastestLapId")
})
public class Results {

    @PrimaryKey(autoGenerate = true)
    public int resultId;

    @ColumnInfo(index = true)
    public int raceId;

    @ColumnInfo
    private int number;

    @ColumnInfo
    private int position;

    @ColumnInfo
    private String positionText;

    @ColumnInfo
    private int points;

    @ColumnInfo(index = true)
    public String driverId;

    @Ignore
    public Driver Driver;

    @ColumnInfo(index = true)
    public String constructorId;

    @Ignore
    public Constructor Constructor;

    @ColumnInfo
    private int grid;

    @ColumnInfo
    private int laps;

    @ColumnInfo
    private String status;

    @ColumnInfo(index = true)
    public int timeId;

    @Ignore
    public Time Time;

    @ColumnInfo(index = true)
    public int fastestLapId;

    @Ignore
    public FastestLap FastestLap;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionText() {
        return positionText;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Driver getDriver() {
        return Driver;
    }

    public void setDriver(Driver driver) {
        this.Driver = driver;
    }

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.Constructor = constructor;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getTime() {
        return Time;
    }

    public void setTime(Time time) {
        this.Time = time;
    }

    public FastestLap getFastestLap() {
        return FastestLap;
    }

    public void setFastestLap(FastestLap fastestLap) {
        this.FastestLap = fastestLap;
    }
}
