package thunderbytes.com.formulanews.Models;

public class FastestLap {
    private int rank;
    private int lap;
    public Time Time;
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
