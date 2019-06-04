package thunderbytes.com.formulanews.Models;

import java.io.Serializable;

public class ConstructorStanding implements Serializable {
    private int position;
    private String positionText;
    private int points;
    private int wins;
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
