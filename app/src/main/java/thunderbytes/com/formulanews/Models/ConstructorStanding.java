package thunderbytes.com.formulanews.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ConstructorStanding", foreignKeys = {
        @ForeignKey(entity = Standings.class, parentColumns = "standingId", childColumns = "standingId", onDelete = CASCADE),
        @ForeignKey(entity = Constructor.class, parentColumns = "constructorId", childColumns = "constructorId", onDelete = CASCADE)
})
public class ConstructorStanding implements Serializable {

    @PrimaryKey
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

    public Constructor getConstructor() {
        return Constructor;
    }

    public void setConstructor(Constructor constructor) {
        Constructor = constructor;
    }
}
