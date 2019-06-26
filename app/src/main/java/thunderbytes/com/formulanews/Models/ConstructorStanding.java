package thunderbytes.com.formulanews.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = {
        @ForeignKey(entity = Standings.class, parentColumns = "standingId", childColumns = "standingId"),
        @ForeignKey(entity = Constructor.class, parentColumns = "constructorId", childColumns = "constructorId")
})
public class ConstructorStanding implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int constructorStandingId;

    @ColumnInfo(index = true)
    public int standingId;

    @ColumnInfo
    private int position;

    @ColumnInfo
    private String positionText;

    @ColumnInfo
    private int points;

    @ColumnInfo
    private int wins;

    @ColumnInfo(index = true)
    public String constructorId;

    @Ignore
    public Constructor Constructor;

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

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public thunderbytes.com.formulanews.Models.Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(thunderbytes.com.formulanews.Models.Constructor constructor) {
        Constructor = constructor;
    }
}
