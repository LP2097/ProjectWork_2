package thunderbytes.com.formulanews.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Race.class, parentColumns = "Id", childColumns = "raceId"),
        @ForeignKey(entity = Driver.class, parentColumns = "driverId", childColumns = "driverId"),
        @ForeignKey(entity = Constructor.class, parentColumns = "constructorId", childColumns = "constructorId")
})
public class Qualifying {
    @PrimaryKey(autoGenerate = true)
    public int qualifyingId;

    @ColumnInfo(index = true)
    public String driverId;

    @ColumnInfo(index = true)
    public int raceId;

    @Ignore
    public Driver Driver;

    @ColumnInfo(index = true)
    public String constructorId;

    @Ignore
    public Constructor Constructor;

    @ColumnInfo
    private int number;

    @ColumnInfo
    private int position;

    @ColumnInfo
    public String Q1;

    @ColumnInfo
    public String Q2;

    @ColumnInfo
    public String Q3;

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String q1) {
        Q1 = q1;
    }

    public String getQ2() {
        return Q2;
    }

    public void setQ2(String q2) {
        Q2 = q2;
    }

    public String getQ3() {
        return Q3;
    }

    public void setQ3(String q3) {
        Q3 = q3;
    }

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

    public Driver getDriver() {
        return Driver;
    }

    public void setDriver(Driver driver) {
        Driver = driver;
    }

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor constructor) {
        Constructor = constructor;
    }


}
