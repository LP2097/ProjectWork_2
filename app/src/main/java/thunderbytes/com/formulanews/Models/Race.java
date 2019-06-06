package thunderbytes.com.formulanews.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.Date;

public class Race implements Serializable {
    private int season;
    private int round;
    private String url;
    private String raceName;
    public Circuit circuit;
    private Date date;
    private String time;
    private int Id;
    public Results results;
    private boolean notify = false;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    @JsonGetter("Circuit")
    public Circuit getCircuit() {
        return circuit;
    }

    @JsonSetter("Circuit")
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }


    @Override
    public String toString() {
        return "Race{}";
    }

}
